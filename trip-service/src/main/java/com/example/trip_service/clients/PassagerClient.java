package com.example.trip_service.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.trip_service.clients.dto.response.PassengerResponse;

import reactor.core.publisher.Mono;

@Service
public class PassagerClient {
  private final WebClient webClient;

  public PassagerClient(WebClient.Builder loadBalancerClientBuilder) {
    this.webClient = loadBalancerClientBuilder.baseUrl("http://passanger-service").build();
  }

  public Mono<PassengerResponse> getById(String id) {
    return webClient.get()
        .uri("/api/v1/passengers/{id}", id)
        .retrieve()
        .bodyToMono(PassengerResponse.class);
  }
}
