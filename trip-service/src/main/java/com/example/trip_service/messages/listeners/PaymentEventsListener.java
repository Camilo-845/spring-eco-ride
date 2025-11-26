package com.example.trip_service.messages.listeners;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.trip_service.messages.events.PaymentAuthorizedEvent;
import com.example.trip_service.messages.events.PaymentFailedEvent;

@Configuration
public class PaymentEventsListener {

  /**
   * Bean Consumer para manejar PaymentAuthorizedEvent.
   * El nombre del método ('handlePaymentAuthorized') debe coincidir con la
   * definición
   * en 'spring.cloud.stream.function.definition' en application.yml.
   */
  @Bean
  public Consumer<PaymentAuthorizedEvent> paymentAuthorized() {
    return event -> {
      // Lógica de negocio para manejar el pago autorizado
      System.out.println("🎉 Evento Autorizado recibido.");
      System.out.println("Reserva ID: " + event.reservationId() +
          ", PaymentIntent ID: " + event.paymentIntentId());
      // Por ejemplo: Actualizar el estado de la reserva a PAGADO
      // reservaService.updateStatus(event.reservationId(), "AUTHORIZED");
    };
  }

  /**
   * Bean Consumer para manejar PaymentFailedEvent.
   * El nombre del método ('handlePaymentFailed') debe coincidir con la definición
   * en 'spring.cloud.stream.function.definition' en application.yml.
   */
  @Bean
  public Consumer<PaymentFailedEvent> paymentFailed() {
    return event -> {
      // Lógica de negocio para manejar el fallo de pago
      System.out.println("🚨 Evento Fallido recibido.");
      System.out.println("Reserva ID: " + event.reservationId() +
          ", Razón: " + event.reason());
      // Por ejemplo: Cancelar la reserva o alertar al usuario
      // reservaService.cancelReservation(event.reservationId());
    };
  }
}
