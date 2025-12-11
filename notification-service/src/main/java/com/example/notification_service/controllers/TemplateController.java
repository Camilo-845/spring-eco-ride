package com.example.notification_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification_service.dtos.request.TemplateRequest;
import com.example.notification_service.dtos.response.TemplateResponse;
import com.example.notification_service.services.TemplateService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/templates")
public class TemplateController {

  private final TemplateService templateService;

  public TemplateController(TemplateService templateService) {
    this.templateService = templateService;
  }

  @GetMapping
  public Flux<TemplateResponse> getAll() {
    return templateService.getALl();
  }

  @GetMapping("/{id}")
  public Mono<TemplateResponse> getById(@PathVariable Long id) {
    return templateService.getById(id);
  }

  @GetMapping("event/{eventType}/channel/{channelId}")
  public Mono<TemplateResponse> getByEventTypeAndChannel(
      @PathVariable Long eventType,
      @PathVariable Long channelId) {
    return templateService.getByEventTypeAndChannel(eventType, channelId);
  }

  @PostMapping
  public Mono<TemplateResponse> create(@Valid @RequestBody TemplateRequest templateRequest) {
    return templateService.create(templateRequest);
  }

}
