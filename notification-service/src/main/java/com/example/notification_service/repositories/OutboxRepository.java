package com.example.notification_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.Outbox;

public interface OutboxRepository extends ReactiveCrudRepository<Outbox, Long> {

}
