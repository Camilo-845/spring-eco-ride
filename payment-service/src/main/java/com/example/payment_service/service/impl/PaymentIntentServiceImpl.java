package com.example.payment_service.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.payment_service.dto.request.CreatePaymentIntent;
import com.example.payment_service.dto.response.PaymentIntentResponse;
import com.example.payment_service.mapper.PaymentIntentMapper;
import com.example.payment_service.model.PaymentIntent;
import com.example.payment_service.repository.PaymentIntentRepository;
import com.example.payment_service.service.PaymentIntentService;

import reactor.core.publisher.Mono;

@Service
public class PaymentIntentServiceImpl implements PaymentIntentService {

  private final PaymentIntentRepository paymentIntentRepository;
  private final PaymentIntentMapper paymentIntentMapper;

  public PaymentIntentServiceImpl(PaymentIntentMapper paymentIntentMapper,
      PaymentIntentRepository paymentIntentRepository) {
    this.paymentIntentMapper = paymentIntentMapper;
    this.paymentIntentRepository = paymentIntentRepository;
  }

  @Override
  public Mono<PaymentIntentResponse> create(CreatePaymentIntent paymentIntentRequest) {
    PaymentIntent paymentIntent = paymentIntentMapper.toEntity(paymentIntentRequest);
    paymentIntent.setCreatedAt(Instant.now());
    paymentIntent.setUpdatedAt(Instant.now());

    return paymentIntentRepository.save(paymentIntent).map(paymentIntentMapper::toDto);
  }

  @Override
  public Mono<PaymentIntentResponse> update(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

}
