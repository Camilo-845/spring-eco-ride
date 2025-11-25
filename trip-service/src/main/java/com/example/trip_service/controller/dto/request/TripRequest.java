package com.example.trip_service.controller.dto.request;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;

public record TripRequest(@NotBlank(message = "origin id required") Long origin,
    @NotBlank(message = "destination is required") Long destination,
    @NotBlank(message = "startTime is required") Instant startTime,
    @NotBlank(message = "seatsTotal is requiered") Integer seatsTotal,
    @NotBlank(message = "pricePerSeat id required") Double pricePerSeat) {
}
