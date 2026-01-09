package com.example.notification_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification_service.dtos.response.OutboxResponse;
import com.example.notification_service.services.OutboxService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/outboxes")
public class OutboxController {
  private final OutboxService outboxService;

  public OutboxController(OutboxService outboxService) {
    this.outboxService = outboxService;
  }

  @GetMapping
  public Flux<OutboxResponse> getAll() {
    return outboxService.getAll();
  }

  @GetMapping("/{id}")
  public Mono<OutboxResponse> getById(@PathVariable Long id) {
    return outboxService.getById(id);
  }
}
