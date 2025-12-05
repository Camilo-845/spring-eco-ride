package com.example.notification_service.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.NotificationEvent;

import reactor.core.publisher.Mono;

public interface NotificationEventRepository extends ReactiveCrudRepository<NotificationEvent, Long> {
  @Query("SELECT * FROM notification_events WHERE event_type = :eventType")
  Mono<NotificationEvent> findByEventType(String eventType);
}
