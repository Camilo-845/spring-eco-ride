package com.example.trip_service.messages.producers;

import org.springframework.stereotype.Service;

import com.example.trip_service.messages.events.ReservationRequestEvent;

import org.springframework.cloud.stream.function.StreamBridge;

@Service
public class ReservationProducer {

  private static final String RESERVATION_REQUEST_BINDING = "reservationRequested-out-0";
  private static final String RESERVATION_CONFIRMED_BINDING = "reservationConfimedNotification-out-0";
  private final StreamBridge streamBridge;

  public ReservationProducer(StreamBridge streamBridge) {
    this.streamBridge = streamBridge;
  }

  public boolean sendReservationRequestEvent(ReservationRequestEvent event) {
    boolean sent = streamBridge.send(RESERVATION_REQUEST_BINDING, event);
    return sent;
  }

  public boolean sendReservationConfirmedNotificationEvent(ReservationRequestEvent event) {
    boolean sent = streamBridge.send(RESERVATION_CONFIRMED_BINDING, event);
    return sent;
  }

}
