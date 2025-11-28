package com.example.trip_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trip_service.controller.dto.response.ReservationResponse;
import com.example.trip_service.service.ReservationService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
  private final ReservationService ReservationService;

  public ReservationController(com.example.trip_service.service.ReservationService reservationService) {
    ReservationService = reservationService;
  }

  @GetMapping("/{id}")
  public Mono<ReservationResponse> getReservationById(@PathVariable String id) {
    return ReservationService.getById(id);
  }

}
