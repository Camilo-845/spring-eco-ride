package com.example.notification_service.services;

import com.example.notification_service.dtos.response.NotificationEventResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationEventService {

  Flux<NotificationEventResponse> getAll();

  Mono<NotificationEventResponse> getById(Long id);

  Mono<NotificationEventResponse> getByEventType(String eventType);

}
