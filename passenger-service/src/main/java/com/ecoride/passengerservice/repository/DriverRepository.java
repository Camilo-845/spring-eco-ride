package com.ecoride.passengerservice.repository;

import com.ecoride.passengerservice.model.Driver;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface DriverRepository extends ReactiveCrudRepository<Driver, UUID> {
  @Query("SELECT * FROM drivers WHERE passenger_id = :passengerId")
  public Mono<Driver> findByPassengerId(UUID passengerId);
}
