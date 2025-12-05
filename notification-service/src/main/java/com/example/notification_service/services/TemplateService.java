package com.example.notification_service.services;

import com.example.notification_service.dtos.request.TemplateRequest;
import com.example.notification_service.dtos.response.TemplateResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TemplateService {
  Flux<TemplateResponse> getALl();

  Mono<TemplateResponse> getById();

  Mono<TemplateResponse> getByEventTypeAndChannel(Long eventType, Long channel);

  Mono<TemplateResponse> create(TemplateRequest templateRequest);
}
