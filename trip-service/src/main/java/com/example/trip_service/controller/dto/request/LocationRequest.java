package com.example.trip_service.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationRequest(
    @NotBlank(message = "name is required") String name,
    @NotNull(message = "latitude is required") Double latitude,
    @NotNull(message = "latitude is longitude") Double longitude) {
}
