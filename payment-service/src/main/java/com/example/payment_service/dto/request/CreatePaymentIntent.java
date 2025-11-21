package com.example.payment_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreatePaymentIntent(

    @NotBlank(message = "reservationId is required") String reservationId,
    @NotBlank(message = "amount is required") Double amount,
    @NotBlank(message = "currency is required") String currency) {
}
