package com.example.payment_service.messages.producers;

import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;

import com.example.payment_service.dto.events.PaymentAuthorizedEvent;
import com.example.payment_service.dto.events.PaymentFailedEvent;
import com.example.payment_service.dto.request.PaymentIntentRequest;

@Service
public class PaymentProducer {

  private static final String FAILED_BINDING = "paymentFailed-out-0";
  private static final String AUTHORIZED_BINDING = "paymentAuthorized-out-0";
  private final StreamBridge streamBridge;

  public PaymentProducer(StreamBridge streamBridge) {
    this.streamBridge = streamBridge;
  }

  public boolean sendPaymentAuthorizedEvent(PaymentAuthorizedEvent event) {
    boolean sent = streamBridge.send(AUTHORIZED_BINDING, event);
    return sent;
  }

  public boolean sendPaymentFailedEvent(PaymentFailedEvent event) {
    boolean sent = streamBridge.send(FAILED_BINDING, event);
    return sent;
  }
}
