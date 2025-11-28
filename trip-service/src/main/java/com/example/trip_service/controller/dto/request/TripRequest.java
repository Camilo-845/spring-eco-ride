package com.example.trip_service.controller.dto.request;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TripRequest(@NotNull(message = "origin id required") Long origin,
    @NotNull(message = "destination is required") Long destination,
    @NotNull(message = "startTime is required") Instant startTime,
    @NotNull(message = "seatsTotal is requiered") Integer seatsTotal,
    @NotNull(message = "pricePerSeat id required") Double pricePerSeat) {
}
