package com.example.payment_service.dto.events;

import lombok.Builder;

@Builder
public record PaymentAuthorizedEvent(
    String reservationId,
    String paymentIntentId,
    String chargeId) {
}
