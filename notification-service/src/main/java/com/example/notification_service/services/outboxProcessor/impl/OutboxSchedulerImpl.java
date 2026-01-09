package com.example.notification_service.services.outboxProcessor.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.notification_service.services.outboxProcessor.OutboxProcessor;
import com.example.notification_service.services.outboxProcessor.OutboxScheduler;

import reactor.core.publisher.Mono;

@Component
public class OutboxSchedulerImpl implements OutboxScheduler {
  private final OutboxProcessor outboxProcessor;

  public OutboxSchedulerImpl(OutboxProcessor outboxProcessor) {
    this.outboxProcessor = outboxProcessor;
  }

  @Override
  @Scheduled(fixedDelay = 10000)
  public void runOutboxJob() {
    outboxProcessor.getBatchToProcess()
        .flatMap(outbox -> {
          return outboxProcessor.processOutbox(outbox)
              .onErrorResume(e -> {
                return Mono.empty();
              });
        }).subscribe();
  }
}
