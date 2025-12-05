package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.response.ChannelResponse;
import com.example.notification_service.exceptions.ResourceNotFoundException;
import com.example.notification_service.mappers.ChannelMapper;
import com.example.notification_service.repositories.ChannelRepository;
import com.example.notification_service.services.ChannelService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ChannelServiceImpl implements ChannelService {
  private final ChannelRepository channelRepository;
  private final ChannelMapper channelMapper;

  public ChannelServiceImpl(ChannelRepository channelRepository, ChannelMapper channelMapper) {
    this.channelRepository = channelRepository;
    this.channelMapper = channelMapper;
  }

  @Override
  public Flux<ChannelResponse> getAll() {
    return channelRepository.findAll().map(channelMapper::toDto);
  }

  @Override
  public Mono<ChannelResponse> getById(Long id) {
    return channelRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Channel", "id", id)))
        .map(channelMapper::toDto);
  }

  @Override
  public Mono<ChannelResponse> getByName(String name) {
    return channelRepository.findByName(name)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Channel", "name", name)))
        .map(channelMapper::toDto);
  }

}
