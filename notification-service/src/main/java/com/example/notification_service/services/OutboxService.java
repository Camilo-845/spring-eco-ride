package com.example.notification_service.services;

import com.example.notification_service.dtos.response.OutboxResponse;
import com.example.notification_service.models.Outbox;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OutboxService {
  Flux<OutboxResponse> getAll();

  Mono<OutboxResponse> getById(Long id);

  Mono<OutboxResponse> create(Outbox outbox);

  Mono<OutboxResponse> update(Long id, Outbox outbox);
}
