package com.example.trip_service.service;

import com.example.trip_service.controller.dto.request.TripRequest;
import com.example.trip_service.controller.dto.response.TripResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TripService {
  Mono<TripResponse> create(TripRequest tripRequest, String subId);

  Mono<TripResponse> findById(String id);

  Flux<TripResponse> findByDriver(String id);

  Flux<TripResponse> findByLocations(Long origin, Long destination);

  Mono<TripResponse> update(String id, TripRequest tripRequest);

  Mono<Void> decrementSeats(String tripId);

  Mono<Void> incrementSeats(String tripId);
}
