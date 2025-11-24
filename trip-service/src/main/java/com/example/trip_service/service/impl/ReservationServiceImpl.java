package com.example.trip_service.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.trip_service.controller.dto.response.ReservationResponse;
import com.example.trip_service.controller.dto.response.TripResponse;
import com.example.trip_service.exceptions.ResourceNotFoundException;
import com.example.trip_service.mapper.ReservationMapper;
import com.example.trip_service.model.Reservation;
import com.example.trip_service.repository.ReservationRepository;
import com.example.trip_service.service.ReservationService;
import com.example.trip_service.service.TripService;

import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepository reservationRepository;
  private final ReservationMapper reservationMapper;
  private final TripService tripService;

  public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
      TripService tripService) {
    this.reservationRepository = reservationRepository;
    this.reservationMapper = reservationMapper;
    this.tripService = tripService;
  }

  @Override
  public Mono<ReservationResponse> create(@NotNull String tripId, @NotNull String subId) {
    // Buscar Existencia e identificador en servicio Passager
    // Mono<PassagerResponse> passager = passagerClient.findBySubId(subId);
    Mono<TripResponse> passager = tripService.findById(tripId);

    Mono<TripResponse> trip = tripService.findById(tripId);

    return Mono.zip(trip, passager)
        .flatMap(result -> {
          Reservation reservation = Reservation.builder()
              .tripId(UUID.fromString(result.getT1().id()))
              // .passagerId(result.getT2().id());
              .passagerId(UUID.fromString(result.getT2().id()))
              .createdAt(Instant.now())
              .createdAt(Instant.now())
              .build();

          return reservationRepository.save(reservation);
        }).map(reservationMapper::toDto);
  }

  @Override
  public Mono<ReservationResponse> getById(@NotNull String id) {
    UUID reservationUuid = UUID.fromString(id);
    return reservationRepository.findById(reservationUuid)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("reservation", "id", id)))
        .map(reservationMapper::toDto);
  }

  @Override
  public Flux<ReservationResponse> getByTrip(@NotNull String tripId) {
    UUID tripUuid = UUID.fromString(tripId);
    return tripService.findById(tripId)
        .flatMapMany(result -> {
          return reservationRepository.findManyByTripId(tripUuid);
        }).map(reservationMapper::toDto);
  }

}
