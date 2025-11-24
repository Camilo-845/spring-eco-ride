package com.example.trip_service.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LocationRequest(
    @NotBlank(message = "name is required") String name,
    @NotBlank(message = "latitude is required") Double latitude,
    @NotBlank(message = "latitude is longitude") Double longitude) {
}
