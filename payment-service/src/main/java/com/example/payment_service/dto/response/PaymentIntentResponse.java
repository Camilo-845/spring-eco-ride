package com.example.payment_service.dto.response;

public record PaymentIntentResponse(
    String id,
    String reservationId,
    Double amount,
    String currency,
    String status) {
}
