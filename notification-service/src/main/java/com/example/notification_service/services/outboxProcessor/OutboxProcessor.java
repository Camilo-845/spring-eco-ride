package com.example.notification_service.services.outboxProcessor;

import com.example.notification_service.models.Outbox;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OutboxProcessor {
  Flux<Outbox> getBatchToProcess();

  Mono<Void> processOutbox(Outbox outbox);

  Mono<Void> markAsSend(Outbox outbox);

  Mono<Void> handlePermanentFailure(Outbox outbox, Throwable e);
}
