package com.example.notification_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.notification_service.models.Channel;

public interface ChannelRepository extends ReactiveCrudRepository<Channel, Long> {
}
