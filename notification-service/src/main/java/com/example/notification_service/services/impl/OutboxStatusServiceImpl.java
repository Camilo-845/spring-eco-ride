package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.response.OutboxStatusResponse;
import com.example.notification_service.exceptions.ResourceNotFoundException;
import com.example.notification_service.mappers.OutboxStatusMapper;
import com.example.notification_service.repositories.OutboxStatusRepository;
import com.example.notification_service.services.OutboxStatusService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OutboxStatusServiceImpl implements OutboxStatusService {
  private final OutboxStatusRepository outboxRepository;
  private final OutboxStatusMapper outboxMapper;

  public OutboxStatusServiceImpl(OutboxStatusRepository outboxRepository, OutboxStatusMapper outboxMapper) {
    this.outboxRepository = outboxRepository;
    this.outboxMapper = outboxMapper;
  }

  @Override
  public Flux<OutboxStatusResponse> getAll() {
    return outboxRepository.findAll().map(outboxMapper::toDto);
  }

  @Override
  public Mono<OutboxStatusResponse> getById(Long id) {
    return outboxRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("OutboxStatus", "id", id)))
        .map(outboxMapper::toDto);
  }

  @Override
  public Mono<OutboxStatusResponse> getByName(String name) {
    return outboxRepository.findByName(name)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("OutboxStatus", "name", name)))
        .map(outboxMapper::toDto);
  }

}
