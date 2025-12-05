package com.example.notification_service.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.Template;

import reactor.core.publisher.Mono;

public interface TemplateRepository extends ReactiveCrudRepository<Template, Long> {

  @Query("SELECT * FROM templates WHERE event_id = :eventId AND channel_id = :channelId")
  Mono<Template> findByEventIdAndChannelId(Long eventId, Long channelId);
}
