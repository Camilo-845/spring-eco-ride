package com.example.payment_service.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.payment_service.dto.request.PaymentIntentRequest;
import com.example.payment_service.dto.response.PaymentIntentResponse;
import com.example.payment_service.exceptions.ApiException;
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
  public Mono<PaymentIntentResponse> create(PaymentIntentRequest paymentIntentRequest) {
    PaymentIntent paymentIntent = paymentIntentMapper.toEntity(paymentIntentRequest);
    paymentIntent.setStatus(5);
    paymentIntent.setCreatedAt(Instant.now());
    paymentIntent.setUpdatedAt(Instant.now());

    return paymentIntentRepository.save(paymentIntent).map(paymentIntentMapper::toDto);
  }

  @Override
  public Mono<PaymentIntentResponse> updateStatus(String id, Integer statusId) {
    PaymentIntent paymentIntent = PaymentIntent.builder().status(statusId).build();
    return paymentIntentRepository.save(paymentIntent).map(paymentIntentMapper::toDto);
  }

  @Override
  public Mono<PaymentIntentResponse> findById(UUID id) {
    return paymentIntentRepository.findById(id)
        .switchIfEmpty(Mono.error(new ApiException("paymentIntend not found"))).map(paymentIntentMapper::toDto);
  }
}
