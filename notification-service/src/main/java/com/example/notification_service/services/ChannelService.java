package com.example.notification_service.services;

import com.example.notification_service.dtos.response.ChannelResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChannelService {
  Flux<ChannelResponse> getAll();

  Mono<ChannelResponse> getById(Long id);

  Mono<ChannelResponse> getByName(String name);
}
