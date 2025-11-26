package com.example.payment_service.messages.listeners;

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.payment_service.dto.events.ReservationRequestEvent;
import com.example.payment_service.dto.request.PaymentIntentRequest;
import com.example.payment_service.service.PaymentIntentService;

@Configuration
public class ReservationRequestListener {

  private PaymentIntentService paymentIntentService;

  public ReservationRequestListener(PaymentIntentService paymentIntentService) {
    this.paymentIntentService = paymentIntentService;
  }

  @Bean
  public Consumer<ReservationRequestEvent> reservationRequested() {
    return event -> {
      PaymentIntentRequest paymentIntentRequestDTO = PaymentIntentRequest.builder()
          .reservationId(event.reservationId())
          .amount(event.amount().doubleValue())
          .currency("COP")
          .build();
      paymentIntentService.create(paymentIntentRequestDTO)
          .doOnError(throwable -> {
            // TODO : Implementar manejo de fallo
          }).subscribe();
    };
  };
}
