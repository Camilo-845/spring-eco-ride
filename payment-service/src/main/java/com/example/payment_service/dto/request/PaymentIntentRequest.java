package com.example.payment_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PaymentIntentRequest(
    @NotBlank(message = "reservationId is required") String reservationId,
    @NotNull(message = "amount is required") Double amount,
    @NotBlank(message = "currency is required") String currency) {
}
