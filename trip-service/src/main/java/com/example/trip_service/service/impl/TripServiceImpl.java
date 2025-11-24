package com.example.trip_service.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.trip_service.controller.dto.request.TripRequest;
import com.example.trip_service.controller.dto.response.LocationResponse;
import com.example.trip_service.controller.dto.response.TripResponse;
import com.example.trip_service.exceptions.ResourceNotFoundException;
import com.example.trip_service.mapper.TripMapper;
import com.example.trip_service.model.Trip;
import com.example.trip_service.repository.TripRepository;
import com.example.trip_service.service.LocationService;
import com.example.trip_service.service.TripService;

import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TripServiceImpl implements TripService {

  private final TripRepository tripRepository;
  private final TripMapper tripMapper;
  private final LocationService locationService;

  public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper, LocationService locationService) {
    this.tripRepository = tripRepository;
    this.tripMapper = tripMapper;
    this.locationService = locationService;
  }

  @Override
  public Mono<TripResponse> create(TripRequest tripRequest) {
    Trip trip = tripMapper.toEntity(tripRequest);
    trip.setCreatedAt(Instant.now());
    trip.setUpdatedAt(Instant.now());

    return tripRepository.save(trip)
        .map(tripMapper::toDto);
  }

  @Override
  public Mono<TripResponse> findById(@NotNull String id) {
    UUID tripId = UUID.fromString(id);
    return tripRepository.findById(tripId)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("trip", "id", id)))
        .map(tripMapper::toDto);
  }

  @Override
  public Flux<TripResponse> findByDriver(@NotNull String id) {
    UUID driverId = UUID.fromString(id);
    return tripRepository.findByDriverId(driverId)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("trip", "id", id)))
        .map(tripMapper::toDto);
  }

  @Override
  public Mono<TripResponse> update(@NotNull String id, TripRequest tripRequest) {
    UUID tripId = UUID.fromString(id);
    Trip trip = tripMapper.toEntity(tripRequest);
    return tripRepository.findById(tripId)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("trip", "id", id)))
        .flatMap(findTrip -> {
          trip.setId(findTrip.getId());
          return tripRepository.save(trip)
              .map(tripMapper::toDto);
        });
  }

  @Override
  public Flux<TripResponse> findByLocations(@NotNull Long origin, @NotNull Long destination) {
    Mono<LocationResponse> originLocation = locationService.getById(origin);
    Mono<LocationResponse> destinationLocation = locationService.getById(destination);

    return Flux.zip(originLocation, destinationLocation)
        .flatMap(result -> {
          return tripRepository.findByOriginAndDestination(origin, destination)
              .map(tripMapper::toDto);
        });
  }

}
