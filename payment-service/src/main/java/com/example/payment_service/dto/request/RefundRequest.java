package com.example.payment_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefundRequest(
  @NotBlank(message = "amount is required")
  Double amount
) {
}
