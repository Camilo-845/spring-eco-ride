package com.example.trip_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.trip_service.controller.dto.request.TripRequest;
import com.example.trip_service.controller.dto.response.ReservationResponse;
import com.example.trip_service.controller.dto.response.TripResponse;
import com.example.trip_service.service.ReservationService;
import com.example.trip_service.service.TripService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trips")
public class TripController {

  private final TripService tripService;
  private final ReservationService reservationService;

  public TripController(TripService tripService, ReservationService reservationService) {
    this.tripService = tripService;
    this.reservationService = reservationService;
  }

  @PostMapping("/")
  public Mono<TripResponse> createTrip(@Valid @RequestBody TripRequest request) {
    // Se captura por el jwt
    String subId = "temporal-sub_id";
    return tripService.create(request, subId);
  }

  @GetMapping("/")
  public Flux<TripResponse> getByLocations(
      @RequestParam(name = "from") Long origin,
      @RequestParam(name = "to") Long destination) {
    return tripService.findByLocations(origin, destination);
  }

  @PostMapping("/{tripId}/reservations")
  public Mono<ReservationResponse> createReservation(@PathVariable String tripId) {
    // Se captura por el jwt
    String subId = "temporal-sub_id";
    return reservationService.create(tripId, subId);
  }
}
