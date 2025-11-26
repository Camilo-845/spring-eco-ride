package com.example.payment_service.dto.events;

public record PaymentFailedEvent(
    String reservationId,
    String reason) {
}
