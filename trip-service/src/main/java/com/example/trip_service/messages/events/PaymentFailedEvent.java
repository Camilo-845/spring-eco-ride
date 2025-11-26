package com.example.trip_service.messages.events;

import lombok.Builder;

@Builder
public record PaymentFailedEvent(
    String reservationId,
    String reason) {
}
