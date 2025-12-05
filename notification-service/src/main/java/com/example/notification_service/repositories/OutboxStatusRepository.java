package com.example.notification_service.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.OutboxStatus;

import reactor.core.publisher.Mono;

public interface OutboxStatusRepository extends ReactiveCrudRepository<OutboxStatus, Long> {
  @Query("SELECT * FROM outbox_status WHERE name = :name")
  public Mono<OutboxStatus> findByName(String name);
}
