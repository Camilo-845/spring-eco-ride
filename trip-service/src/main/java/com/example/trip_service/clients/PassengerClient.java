package com.example.trip_service.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.trip_service.clients.dto.response.DriverResponse;
import com.example.trip_service.clients.dto.response.PassengerResponse;

import reactor.core.publisher.Mono;

@Service
public class PassengerClient {
  private final WebClient webClient;

  public PassengerClient(WebClient.Builder loadBalancerClientBuilder) {
    this.webClient = loadBalancerClientBuilder.baseUrl("http://PASSAGER-SERVICE").build();
  }

  public Mono<PassengerResponse> getById(String id) {
    return webClient.get()
        .uri("/api/v1/passengers/{id}", id)
        .retrieve()
        .bodyToMono(PassengerResponse.class);
  }

  public Mono<PassengerResponse> getBySubId(String subId) {
    return webClient.get()
        .uri("/api/v1/passengers/me/{subId}", subId)
        .retrieve()
        .bodyToMono(PassengerResponse.class);
  }

  public Mono<DriverResponse> getByPassengerId(String id) {
    return webClient.get()
        .uri("/api/v1/drivers-profile/passenger/{id}", id)
        .retrieve()
        .bodyToMono(DriverResponse.class);
  }

}
