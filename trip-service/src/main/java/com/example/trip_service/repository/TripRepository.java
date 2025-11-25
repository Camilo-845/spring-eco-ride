package com.example.trip_service.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.trip_service.model.Trip;

import reactor.core.publisher.Flux;

public interface TripRepository extends ReactiveCrudRepository<Trip, UUID> {

  @Query("SELECT * FROM trips WHERE driver_id = :driverId")
  public Flux<Trip> findByDriverId(UUID driverId);

  @Query("SELECT * FROM trips WHERE origin = :origin AND destination = :destination")
  public Flux<Trip> findByOriginAndDestination(Long origin, Long destination);

}
