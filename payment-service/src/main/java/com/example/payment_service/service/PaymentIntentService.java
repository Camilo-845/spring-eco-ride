
package com.example.payment_service.service;

import java.util.UUID;

import com.example.payment_service.dto.request.PaymentIntentRequest;
import com.example.payment_service.dto.response.PaymentIntentResponse;

import reactor.core.publisher.Mono;

public interface PaymentIntentService {
  Mono<PaymentIntentResponse> create(PaymentIntentRequest paymentIntent);

  Mono<PaymentIntentResponse> updateStatus(String id, Integer statusId);

  Mono<PaymentIntentResponse> findById(UUID id);
}
