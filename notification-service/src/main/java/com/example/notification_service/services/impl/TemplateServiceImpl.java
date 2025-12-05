package com.example.notification_service.services.impl;

import com.example.notification_service.dtos.request.TemplateRequest;
import com.example.notification_service.dtos.response.TemplateResponse;
import com.example.notification_service.services.TemplateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TemplateServiceImpl implements TemplateService {

  @Override
  public Flux<TemplateResponse> getALl() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getALl'");
  }

  @Override
  public Mono<TemplateResponse> getById() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public Mono<TemplateResponse> getByEventTypeAndChannel(Long eventType, Long channel) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByEventTypeAndChannel'");
  }

  @Override
  public Mono<TemplateResponse> create(TemplateRequest templateRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

}
