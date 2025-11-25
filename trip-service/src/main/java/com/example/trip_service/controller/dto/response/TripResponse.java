package com.example.trip_service.controller.dto.response;

import java.time.Instant;

public record TripResponse(String id, String driverId, Long origin, Long destination, Instant startTime,
    Integer seatsTotal, Integer seatsAvailable) {
}
