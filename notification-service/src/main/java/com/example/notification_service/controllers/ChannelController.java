package com.example.notification_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification_service.dtos.response.ChannelResponse;
import com.example.notification_service.services.ChannelService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("channels")
public class ChannelController {
  private final ChannelService channelService;

  public ChannelController(ChannelService channelService) {
    this.channelService = channelService;
  }

  @GetMapping
  public Flux<ChannelResponse> getAll() {
    return channelService.getAll();
  }

  @GetMapping("/{id}")
  public Mono<ChannelResponse> getById(@PathVariable Long id) {
    return channelService.getById(id);
  }

  @GetMapping("/name/{name}")
  public Mono<ChannelResponse> getByName(@PathVariable String name) {
    return channelService.getByName(name);
  }
}
