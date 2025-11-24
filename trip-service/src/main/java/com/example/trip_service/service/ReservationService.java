package com.example.trip_service.service;

import com.example.trip_service.controller.dto.response.ReservationResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {
  Mono<ReservationResponse> create(String tripId, String subId);

  Mono<ReservationResponse> getById(String id);

  Flux<ReservationResponse> getByTrip(String tripId);

}
