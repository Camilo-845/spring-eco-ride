package com.example.payment_service.messages.listeners;

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.payment_service.dto.events.PaymentAuthorizedEvent;
import com.example.payment_service.dto.events.PaymentFailedEvent;
import com.example.payment_service.dto.events.ReservationRequestEvent;
import com.example.payment_service.dto.request.PaymentIntentRequest;
import com.example.payment_service.messages.producers.PaymentProducer;
import com.example.payment_service.service.PaymentIntentService;

@Configuration
public class ReservationRequestListener {

  private PaymentIntentService paymentIntentService;
  private PaymentProducer paymentProducer;

  public ReservationRequestListener(PaymentIntentService paymentIntentService, PaymentProducer paymentProducer) {
    this.paymentIntentService = paymentIntentService;
    this.paymentProducer = paymentProducer;
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
            PaymentFailedEvent failEvent = PaymentFailedEvent.builder()
                .reservationId(event.reservationId())
                .reason(throwable.getMessage())
                .build();
            paymentProducer.sendPaymentFailedEvent(failEvent);

          }).subscribe();
    };
  };
}
