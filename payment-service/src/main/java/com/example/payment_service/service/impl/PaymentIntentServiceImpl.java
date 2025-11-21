package com.example.payment_service.service.impl;

import com.example.payment_service.dto.request.CreatePaymentIntent;
import com.example.payment_service.dto.response.PaymentIntentResponse;
import com.example.payment_service.service.PaymentIntentService;

import reactor.core.publisher.Mono;

public class PaymentIntentServiceImpl implements PaymentIntentService {

  @Override
  public Mono<PaymentIntentResponse> create(CreatePaymentIntent paymentIntent) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public Mono<PaymentIntentResponse> update(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

}
