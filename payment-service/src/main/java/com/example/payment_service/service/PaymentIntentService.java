
package com.example.payment_service.service;

import com.example.payment_service.dto.request.CreatePaymentIntent;
import com.example.payment_service.dto.response.PaymentIntentResponse;

import reactor.core.publisher.Mono;

public interface PaymentIntentService {
  Mono<PaymentIntentResponse> create(CreatePaymentIntent paymentIntent);

  Mono<PaymentIntentResponse> update(String id);
}
