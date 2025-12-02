package com.example.trip_service.clients.dto.response;

import java.util.UUID;

public record DriverResponse(
    UUID id,
    UUID passengerId,
    String licenseNo,
    String carPlate,
    Integer seatsOffered,
    Boolean verificationStatus) {
}
