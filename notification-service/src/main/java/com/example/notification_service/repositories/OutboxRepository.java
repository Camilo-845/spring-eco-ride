package com.example.notification_service.repositories;

import java.time.Instant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.Outbox;

import reactor.core.publisher.Flux;

public interface OutboxRepository extends ReactiveCrudRepository<Outbox, Long> {
  @Query("""
          SELECT * FROM outboxes
          WHERE status = :statusId
          AND (scheduled_at IS NULL OR scheduled_at <= :now)
          ORDER BY created_at ASC
          LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}
      """)
  Flux<Outbox> findAllPending(Integer statusId, Instant now, Pageable pageable);
}
