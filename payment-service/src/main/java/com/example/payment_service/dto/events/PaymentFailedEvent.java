package com.example.payment_service.dto.events;

import lombok.Builder;

@Builder
public record PaymentFailedEvent(
    String reservationId,
    String reason) {
}
