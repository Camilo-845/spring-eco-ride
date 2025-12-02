package com.example.trip_service.clients.dto.request;

public record PassengerRequest(
    String keycloakSub,
    String name,
    String email) {
}
