package com.example.payment_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PaymentIntentRequest(
    @NotBlank(message = "reservationId is required") String reservationId,
    @NotBlank(message = "amount is required") Double amount,
    @NotBlank(message = "currency is required") String currency) {
}
