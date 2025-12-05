package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.response.OutboxStatusResponse;
import com.example.notification_service.services.OutboxStatusService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OutboxStatusServiceImpl implements OutboxStatusService {

  @Override
  public Flux<OutboxStatusResponse> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public Mono<OutboxStatusResponse> getById() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public Mono<OutboxStatusResponse> getByName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByName'");
  }

}
