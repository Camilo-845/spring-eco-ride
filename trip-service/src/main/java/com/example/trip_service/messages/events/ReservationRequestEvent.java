package com.example.trip_service.messages.events;

public record ReservationRequestEvent(
    String reservationId,
    String tripId,
    String passagerId,
    Double amount) {
}
