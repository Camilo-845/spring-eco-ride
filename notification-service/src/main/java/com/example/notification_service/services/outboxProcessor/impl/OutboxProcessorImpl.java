package com.example.notification_service.services.outboxProcessor.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.notification_service.models.Outbox;
import com.example.notification_service.repositories.OutboxRepository;
import com.example.notification_service.services.ChannelService;
import com.example.notification_service.services.OutboxStatusService;
import com.example.notification_service.services.TemplateService;
import com.example.notification_service.services.notification.NotificationSender;
import com.example.notification_service.services.outboxProcessor.OutboxProcessor;
import com.example.notification_service.utils.TemplateRender;

import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.Retry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OutboxProcessorImpl implements OutboxProcessor {

  private final OutboxStatusService outboxStatusService;
  private final OutboxRepository outboxRepository;
  private final TemplateService templateService;
  private final ChannelService channelService;
  private final TemplateRender templateRender;
  private final Map<String, NotificationSender> senders;
  private final Retry notificationRetry;

  public OutboxProcessorImpl(OutboxStatusService outboxStatusService, OutboxRepository outboxRepository,
      TemplateRender templateRender, TemplateService templateService, Map<String, NotificationSender> senders,
      ChannelService channelService, Retry notificationRetry) {
    this.outboxStatusService = outboxStatusService;
    this.outboxRepository = outboxRepository;
    this.templateService = templateService;
    this.channelService = channelService;
    this.templateRender = templateRender;
    this.senders = senders;
    this.notificationRetry = notificationRetry;
  }

  @Override
  public Flux<Outbox> getBatchToProcess() {
    return outboxStatusService.getByName("pending").flatMapMany(outboxStatus -> {
      return outboxRepository.findAllPending(outboxStatus.id().intValue(), Instant.now(), PageRequest.of(0, 50));
    });
  }

  @Override
  public Mono<Void> processOutbox(Outbox outbox) {
    return templateService.getById(outbox.getTemplateId()).flatMap(template -> {
      String body = templateRender.render(template.body(), outbox.getPayload().asString());
      String subject = templateRender.render(template.subject(), outbox.getPayload().asString());
      return channelService.getById(template.channel()).flatMap(channel -> {
        NotificationSender sender = senders.get(channel.name().toLowerCase());
        if (sender == null) {
          return Mono.error(new RuntimeException("No sender found for channel: " + channel.name()));
        }
        return Mono.defer(() -> sender.send(body, outbox.getRecipient(), subject))
            .transformDeferred(RetryOperator.of(notificationRetry))
            .then(markAsSend(outbox))
            .onErrorResume(e -> handlePermanentFailure(outbox, e));
      });
    });
  }

  @Override
  public Mono<Void> markAsSend(Outbox outbox) {
    return outboxStatusService.getByName("sent").flatMap(outboxStatus -> {
      outbox.setStatus(outboxStatus.id());
      outbox.setSentAt(Instant.now());
      return outboxRepository.save(outbox).then();
    });
  }

  @Override
  public Mono<Void> handlePermanentFailure(Outbox outbox, Throwable e) {
    outbox.setRetries(outbox.getRetries() + 1);
    Mono<Outbox> updatedOutboxMono;
    if (outbox.getRetries() >= 5) {
      updatedOutboxMono = outboxStatusService.getByName("failded").map(outboxStatus -> {
        outbox.setStatus(outboxStatus.id());
        return outbox;
      });
    } else {
      long waitMinutes = (long) Math.pow(2, outbox.getRetries()) * 5;
      outbox.setScheduledAt(Instant.now().plus(Duration.ofMinutes(waitMinutes)));
      updatedOutboxMono = Mono.just(outbox);
    }
    return updatedOutboxMono.flatMap(outboxRepository::save).then();
  }

}
