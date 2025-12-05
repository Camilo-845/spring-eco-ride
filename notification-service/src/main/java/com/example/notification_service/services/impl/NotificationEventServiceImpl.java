package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.response.NotificationEventResponse;
import com.example.notification_service.exceptions.ResourceNotFoundException;
import com.example.notification_service.mappers.NotificationEventMapper;
import com.example.notification_service.repositories.NotificationEventRepository;
import com.example.notification_service.services.NotificationEventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class NotificationEventServiceImpl implements NotificationEventService {
  private final NotificationEventRepository notificationEventRepository;
  private final NotificationEventMapper notificationEventMapper;

  public NotificationEventServiceImpl(NotificationEventRepository notificationEventRepository,
      NotificationEventMapper notificationEventMapper) {
    this.notificationEventRepository = notificationEventRepository;
    this.notificationEventMapper = notificationEventMapper;
  }

  @Override
  public Flux<NotificationEventResponse> getAll() {
    return notificationEventRepository.findAll().map(notificationEventMapper::toDto);
  }

  @Override
  public Mono<NotificationEventResponse> getById(Long id) {
    return notificationEventRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("NotificationEvent", "id", id)))
        .map(notificationEventMapper::toDto);
  }

  @Override
  public Mono<NotificationEventResponse> getByEventType(String eventType) {
    return notificationEventRepository.findByEventType(eventType)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("NotificationEvent", "eventType", eventType)))
        .map(notificationEventMapper::toDto);
  }

}
