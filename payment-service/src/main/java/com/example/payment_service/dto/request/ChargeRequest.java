package com.example.payment_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChargeRequest(
  @NotBlank(message = "provider id required")
  String privider,
  @NotBlank(message = "providerRef id required")
  String providerRef) {
}
