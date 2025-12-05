package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.response.ChannelResponse;
import com.example.notification_service.services.ChannelService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ChannelServiceImpl implements ChannelService {

  @Override
  public Flux<ChannelResponse> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public Mono<ChannelResponse> getById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public Mono<ChannelResponse> getByName(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByName'");
  }

}
