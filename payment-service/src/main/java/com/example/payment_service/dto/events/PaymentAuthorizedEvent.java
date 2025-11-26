package com.example.payment_service.dto.events;

public record PaymentAuthorizedEvent(
    String reservationId,
    String paymentIntentId,
    String chargeId) {
}
