package com.example.trip_service.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.trip_service.model.Reservation;

import reactor.core.publisher.Flux;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, UUID> {

  @Query("SELECT * FROM reservations WHERE trip_id = :tripId")
  Flux<Reservation> findManyByTripId(UUID tripId);

}
