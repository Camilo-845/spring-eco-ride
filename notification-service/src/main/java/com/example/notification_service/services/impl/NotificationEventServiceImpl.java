package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.response.NotificationEventResponse;
import com.example.notification_service.services.NotificationEventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class NotificationEventServiceImpl implements NotificationEventService {

  @Override
  public Flux<NotificationEventResponse> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public Mono<NotificationEventResponse> getById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public Mono<NotificationEventResponse> getByEventType(String eventType) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByEventType'");
  }

}
