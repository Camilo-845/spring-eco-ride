package com.example.trip_service.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.trip_service.controller.dto.request.ReservationRequest;
import com.example.trip_service.controller.dto.response.ReservationResponse;
import com.example.trip_service.controller.dto.response.TripResponse;
import com.example.trip_service.exceptions.ResourceNotFoundException;
import com.example.trip_service.mapper.ReservationMapper;
import com.example.trip_service.model.Reservation;
import com.example.trip_service.repository.ReservationRepository;
import com.example.trip_service.service.ReservationService;
import com.example.trip_service.service.TripService;

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
  public Mono<ReservationResponse> create(String tripId, ReservationRequest request) {
    // Identificator temporal mientras se obtiene por el jwt
    String subId = "indentificador_temporal";

    // Buscar Existencia e identificador en servicio Passager
    Mono<TripResponse> passager = tripService.findById(tripId);

    Mono<TripResponse> trip = tripService.findById(tripId);

    return Mono.zip(trip, passager)
        .flatMap(result -> {
          Reservation reservation = reservationMapper.toEntity(request);
          reservation.setCreatedAt(Instant.now());
          reservation.setUpdatedAt(Instant.now());
          reservation.setTripId(UUID.fromString(result.getT1().id()));
          return reservationRepository.save(reservation);
        }).map(reservationMapper::toDto);
  }

  @Override
  public Mono<ReservationResponse> getById(String id) {
    UUID reservationUuid = UUID.fromString(id);
    return reservationRepository.findById(reservationUuid)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("reservation", "id", id)))
        .map(reservationMapper::toDto);
  }

  @Override
  public Flux<ReservationResponse> getByTrip(String tripId) {
    UUID tripUuid = UUID.fromString(tripId);
    return tripService.findById(tripId)
        .flatMapMany(result -> {
          return reservationRepository.findManyByTripId(tripUuid);
        }).map(reservationMapper::toDto);
  }

}
