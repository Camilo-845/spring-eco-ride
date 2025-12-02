package com.example.trip_service.clients.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record PassengerResponse(
    UUID id,
    String keycloakSub,
    String name,
    String email,
    Double ratingAvg,
    LocalDateTime createdAt) {
}
