package com.example.notification_service.services.impl;

import java.time.Instant;

import com.example.notification_service.dtos.response.NotificationEventResponse;
import com.example.notification_service.dtos.response.OutboxResponse;
import com.example.notification_service.dtos.response.OutboxStatusResponse;
import com.example.notification_service.dtos.response.TemplateResponse;
import com.example.notification_service.exceptions.ResourceNotFoundException;
import com.example.notification_service.mappers.OutboxMapper;
import com.example.notification_service.models.Outbox;
import com.example.notification_service.repositories.OutboxRepository;
import com.example.notification_service.services.NotificationEventService;
import com.example.notification_service.services.OutboxService;
import com.example.notification_service.services.OutboxStatusService;
import com.example.notification_service.services.TemplateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OutboxServiceImpl implements OutboxService {
  private final OutboxRepository outboxRepository;
  private final OutboxMapper outboxMapper;
  private final TemplateService templateService;
  private final NotificationEventService notificationEventService;
  private final OutboxStatusService outboxStatusService;

  public OutboxServiceImpl(OutboxRepository outboxRepository, OutboxMapper outboxMapper,
      NotificationEventService notificationEventService, NotificationEventService notificationEventService2,
      OutboxStatusService outboxStatusService, TemplateService templateService) {
    this.outboxRepository = outboxRepository;
    this.outboxMapper = outboxMapper;
    this.templateService = templateService;
    this.notificationEventService = notificationEventService;
    this.outboxStatusService = outboxStatusService;
  }

  @Override
  public Flux<OutboxResponse> getAll() {
    return outboxRepository.findAll().map(outboxMapper::toDto);
  }

  @Override
  public Mono<OutboxResponse> getById(Long id) {
    return outboxRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Outbox", "id", id)))
        .map(outboxMapper::toDto);
  }

  @Override
  public Mono<OutboxResponse> create(Outbox outbox) {
    outbox.setCreatedAt(Instant.now());
    Mono<TemplateResponse> template = templateService.getById(outbox.getTemplateId());
    Mono<NotificationEventResponse> notificationEvent = notificationEventService.getById(outbox.getEventType());
    Mono<OutboxStatusResponse> outboxStatus = outboxStatusService.getById(outbox.getStatus());

    return Mono.zip(template, notificationEvent, outboxStatus).flatMap(tuple -> {
      return outboxRepository.save(outbox).map(outboxMapper::toDto);
    });
  }

  @Override
  public Mono<OutboxResponse> update(Long id, Outbox outbox) {
    outbox.setCreatedAt(Instant.now());
    Mono<OutboxResponse> existingOutboxMono = getById(id);
    Mono<TemplateResponse> template = templateService.getById(outbox.getTemplateId());
    Mono<NotificationEventResponse> notificationEvent = notificationEventService.getById(outbox.getEventType());
    Mono<OutboxStatusResponse> outboxStatus = outboxStatusService.getById(outbox.getStatus());

    return Mono.zip(existingOutboxMono, template, notificationEvent, outboxStatus).flatMap(tuple -> {
      outbox.setId(id);
      return outboxRepository.save(outbox).map(outboxMapper::toDto);
    });
  }

}
