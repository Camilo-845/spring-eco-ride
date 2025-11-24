package com.example.trip_service.service;

import com.example.trip_service.controller.dto.request.LocationRequest;
import com.example.trip_service.controller.dto.response.LocationResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationService {
  Mono<LocationResponse> create(LocationRequest request);

  Mono<LocationResponse> getById(Long id);

  Flux<LocationResponse> getAll();

  Mono<LocationResponse> update(Long id, LocationRequest request);
}
