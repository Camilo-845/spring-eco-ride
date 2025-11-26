package com.example.payment_service.messages.producers;

import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;

import com.example.payment_service.dto.events.PaymentAuthorizedEvent;
import com.example.payment_service.dto.events.PaymentFailedEvent;
import com.example.payment_service.dto.request.PaymentIntentRequest;

@Service
public class PaymentProducer {

  private static final String PAYMENT_AUTHORIZED_OUT_BINDING = "paymentAuthorized-out-0";
  private static final String PAYMENT_FAILED_OUT_BINDING = "paymentFailed-out-0";
  private final StreamBridge streamBridge;

  public PaymentProducer(StreamBridge streamBridge) {
    this.streamBridge = streamBridge;
  }

  public boolean sendPaymentAuthorizedEvent(PaymentAuthorizedEvent event) {
    boolean sent = streamBridge.send(
        PAYMENT_AUTHORIZED_OUT_BINDING,
        MessageBuilder.withPayload(event)
            .build());
    return sent;
  }

  public boolean sendPaymentFailedEvent(PaymentFailedEvent event) {
    boolean sent = streamBridge.send(
        PAYMENT_FAILED_OUT_BINDING,
        MessageBuilder.withPayload(event)
            .build());
    return sent;
  }
}
