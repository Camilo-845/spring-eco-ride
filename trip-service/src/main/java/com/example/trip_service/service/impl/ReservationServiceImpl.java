package com.example.trip_service.service.impl;

import com.example.trip_service.clients.PassengerClient;
import com.example.trip_service.clients.dto.response.PassengerResponse;
import com.example.trip_service.controller.dto.response.ReservationResponse;
import com.example.trip_service.controller.dto.response.TripResponse;
import com.example.trip_service.exceptions.ResourceNotFoundException;
import com.example.trip_service.mapper.ReservationMapper;
import com.example.trip_service.messages.events.ReservationRequestEvent;
import com.example.trip_service.messages.producers.ReservationProducer;
import com.example.trip_service.model.Reservation;
import com.example.trip_service.repository.ReservationRepository;
import com.example.trip_service.service.ReservationService;
import com.example.trip_service.service.TripService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepository reservationRepository;
  private final ReservationMapper reservationMapper;
  private final TripService tripService;
  private final ReservationProducer reservationProducer;
  private final PassengerClient passengerClient;

  public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
      TripService tripService, ReservationProducer reservationProducer, PassengerClient passengerClient) {
    this.reservationRepository = reservationRepository;
    this.reservationMapper = reservationMapper;
    this.tripService = tripService;
    this.reservationProducer = reservationProducer;
    this.passengerClient = passengerClient;
  }

  @Override
  public Mono<ReservationResponse> create(@NotNull String tripId, @NotNull String subId) {
    Mono<PassengerResponse> passengerMono = passengerClient.getBySubId(subId);
    Mono<TripResponse> tripMono = tripService.findById(tripId);

    return Mono.zip(passengerMono, tripMono)
        .flatMap(result -> {
          Reservation reservation = Reservation.builder()
              .tripId(UUID.fromString(result.getT2().id()))
              .passagerId(result.getT1().id())
              .status(1) // PENDING status
              .createdAt(Instant.now())
              .updatedAt(Instant.now())
              .build();
          return reservationRepository.save(reservation)
              .doOnSuccess(savedReservation -> {
                ReservationRequestEvent event = new ReservationRequestEvent(
                    savedReservation.getId().toString(),
                    result.getT2().id(),
                    savedReservation.getPassagerId().toString(),
                    result.getT2().pricePerSeat());
                reservationProducer.sendReservationRequestEvent(event);
              });
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

  @Override
  public Mono<Void> updateStatusToConfirmed(String reservationId) {
    return reservationRepository.findById(UUID.fromString(reservationId))
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Reservation", "id", reservationId)))
        .flatMap(reservation -> {
          reservation.setStatus(2); // CONFIRMED
          reservation.setUpdatedAt(Instant.now());
          return reservationRepository.save(reservation);
        })
        .then();
  }

  @Override
  public Mono<Void> updateStatusToCancelled(String reservationId) {
    return reservationRepository.findById(UUID.fromString(reservationId))
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Reservation", "id", reservationId)))
        .flatMap(reservation -> {
          reservation.setStatus(3); // CANCELLED
          reservation.setUpdatedAt(Instant.now());
          return reservationRepository.save(reservation);
        })
        .flatMap(savedReservation -> tripService.incrementSeats(savedReservation.getTripId().toString()))
        .then();
  }

}
