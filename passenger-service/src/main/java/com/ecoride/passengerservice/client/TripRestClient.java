package com.ecoride.passengerservice.client;

import com.ecoride.passengerservice.dto.trip.TripResponseDto;
import com.ecoride.passengerservice.exception.ExternalServiceException;
import com.ecoride.passengerservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TripRestClient {

    private final WebClient webClient;

    public TripRestClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("{URI_TRIP_SERVICE}").build();
    }

    public Mono<TripResponseDto> getTripById(String id) {
        return this.webClient.get()
                .uri("/api/v1/trips/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        resp -> Mono.error(new ResourceNotFoundException("Trip not found: " + id)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        resp -> Mono.error(new ExternalServiceException("Trip service error")))
                .bodyToMono(TripResponseDto.class);
    }

}
