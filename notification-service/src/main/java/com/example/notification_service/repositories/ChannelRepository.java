package com.example.notification_service.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.Channel;

import reactor.core.publisher.Mono;

public interface ChannelRepository extends ReactiveCrudRepository<Channel, Long> {
  @Query("SELECT * FROM channels WHERE name = :name")
  public Mono<Channel> findByName(String name);
}
