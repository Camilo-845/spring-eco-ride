package com.example.notification_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.OutboxStatus;

public interface OutboxStatusRepository extends ReactiveCrudRepository<OutboxStatus, Long> {

}
