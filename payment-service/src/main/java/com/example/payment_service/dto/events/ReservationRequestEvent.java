package com.example.payment_service.dto.events;

public record ReservationRequestEvent(
    String reservationId,
    String tripId,
    String passagerId,
    Double amount) {
}
