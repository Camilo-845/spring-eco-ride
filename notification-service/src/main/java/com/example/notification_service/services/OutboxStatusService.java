package com.example.notification_service.services;

import com.example.notification_service.dtos.response.OutboxStatusResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OutboxStatusService {
  Flux<OutboxStatusResponse> getAll();

  Mono<OutboxStatusResponse> getById();

  Mono<OutboxStatusResponse> getByName();
}
