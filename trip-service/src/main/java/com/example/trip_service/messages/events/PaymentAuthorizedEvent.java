package com.example.trip_service.messages.events;

import lombok.Builder;

@Builder
public record PaymentAuthorizedEvent(
    String reservationId,
    String paymentIntentId,
    String chargeId) {
}
