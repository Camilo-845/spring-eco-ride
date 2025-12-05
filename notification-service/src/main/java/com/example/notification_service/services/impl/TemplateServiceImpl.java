package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.request.TemplateRequest;
import com.example.notification_service.dtos.response.ChannelResponse;
import com.example.notification_service.dtos.response.NotificationEventResponse;
import com.example.notification_service.dtos.response.TemplateResponse;
import com.example.notification_service.exceptions.ResourceNotFoundException;
import com.example.notification_service.mappers.TemplateMapper;
import com.example.notification_service.models.Template;
import com.example.notification_service.repositories.TemplateRepository;
import com.example.notification_service.services.ChannelService;
import com.example.notification_service.services.NotificationEventService;
import com.example.notification_service.services.TemplateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TemplateServiceImpl implements TemplateService {
  private final TemplateRepository templateRepository;
  private final TemplateMapper templateMapper;
  private final NotificationEventService notificationEventService;
  private final ChannelService channelService;

  public TemplateServiceImpl(TemplateRepository templateRepository, TemplateMapper templateMapper,
      ChannelService channelService, NotificationEventService notificationEventService) {
    this.templateRepository = templateRepository;
    this.templateMapper = templateMapper;
    this.notificationEventService = notificationEventService;
    this.channelService = channelService;
  }

  @Override
  public Flux<TemplateResponse> getALl() {
    return templateRepository.findAll().map(templateMapper::toDto);
  }

  @Override
  public Mono<TemplateResponse> getById(Long id) {
    return templateRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Template", "id", id)))
        .map(templateMapper::toDto);
  }

  @Override
  public Mono<TemplateResponse> getByEventTypeAndChannel(Long eventType, Long channel) {
    return templateRepository.findByEventIdAndChannelId(eventType, channel)
        .switchIfEmpty(Mono
            .error(new ResourceNotFoundException("Template", "eventType and channel", eventType + " and " + channel)))
        .map(templateMapper::toDto);
  }

  @Override
  public Mono<TemplateResponse> create(TemplateRequest templateRequest) {
    Template template = templateMapper.toEntity(templateRequest);
    Mono<NotificationEventResponse> notificationEvent = notificationEventService.getById(template.getEventType());
    Mono<ChannelResponse> channel = channelService.getById(template.getChannel());
    return Mono.zip(notificationEvent, channel).flatMap(tuple -> {
      return templateRepository.save(template).map(templateMapper::toDto);
    });
  }

}
