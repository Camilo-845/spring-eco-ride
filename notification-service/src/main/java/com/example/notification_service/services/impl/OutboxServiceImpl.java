package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.response.OutboxResponse;
import com.example.notification_service.models.Outbox;
import com.example.notification_service.services.OutboxService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OutboxServiceImpl implements OutboxService {

  @Override
  public Flux<OutboxResponse> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public Mono<OutboxResponse> getById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public Mono<OutboxResponse> create(Outbox outbox) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public Mono<OutboxResponse> update(Long id, Outbox outbox) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

}
