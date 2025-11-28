package com.example.trip_service.messages.listeners;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.trip_service.messages.events.PaymentAuthorizedEvent;
import com.example.trip_service.messages.events.PaymentFailedEvent;
import com.example.trip_service.service.ReservationService;

@Configuration
public class PaymentEventsListener {

  private final ReservationService reservationService;

  public PaymentEventsListener(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @Bean
  public Consumer<PaymentAuthorizedEvent> paymentAuthorized() {
    return event -> {
      // Lógica de negocio para manejar el pago autorizado
      reservationService.updateStatusToConfirmed(event.reservationId()).subscribe();
    };
  }

  @Bean
  public Consumer<PaymentFailedEvent> paymentFailed() {
    return event -> {
      reservationService.updateStatusToCancelled(event.reservationId()).subscribe();
    };
  }
}
