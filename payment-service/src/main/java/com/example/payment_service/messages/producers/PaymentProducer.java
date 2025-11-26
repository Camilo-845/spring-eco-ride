package com.example.payment_service.messages.producers;

import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;

import com.example.payment_service.dto.events.PaymentAuthorizedEvent;
import com.example.payment_service.dto.events.PaymentFailedEvent;
import com.example.payment_service.dto.request.PaymentIntentRequest;

@Service
public class PaymentProducer {

  private static final String PAYMENT_OUT_BINDING = "payment-out-0";
  private final StreamBridge streamBridge;

  public PaymentProducer(StreamBridge streamBridge) {
    this.streamBridge = streamBridge;
  }

  public boolean sendPaymentAuthorizedEvent(PaymentAuthorizedEvent event) {
    boolean sent = streamBridge.send(
        PAYMENT_OUT_BINDING,
        MessageBuilder.withPayload(event)
            .setHeader("spring.cloud.stream.sendto.destination", "payment.authorized") // Uso del header recomendado
            .build());
    return sent;
  }

  public boolean sendPaymentFailedEvent(PaymentFailedEvent event) {
    boolean sent = streamBridge.send(
        PAYMENT_OUT_BINDING,
        MessageBuilder.withPayload(event)
            .setHeader("spring.cloud.stream.sendto.destination", "payment.authorized") // Uso del header recomendado
            .build());
    return sent;
  }
}
