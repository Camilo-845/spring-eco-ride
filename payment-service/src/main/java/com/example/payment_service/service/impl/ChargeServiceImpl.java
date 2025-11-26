package com.example.payment_service.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.payment_service.dto.request.ChargeRequest;
import com.example.payment_service.dto.response.ChargeResponse;
import com.example.payment_service.exceptions.ApiException;
import com.example.payment_service.mapper.ChargeMapper;
import com.example.payment_service.model.Charge;
import com.example.payment_service.repository.ChargeRepository;
import com.example.payment_service.repository.PaymentIntentRepository;
import com.example.payment_service.service.ChargeService;
import com.example.payment_service.service.PaymentIntentService;

import reactor.core.publisher.Mono;

@Service
public class ChargeServiceImpl implements ChargeService {

  private final ChargeRepository chargeRepository;
  private final PaymentIntentService paymentIntentService;
  private final ChargeMapper chargeMapper;

  public ChargeServiceImpl(ChargeRepository chargeRepository, PaymentIntentRepository paymentIntentRepository,
      ChargeMapper chargeMapper, PaymentIntentService paymentIntentService) {
    this.chargeRepository = chargeRepository;
    this.paymentIntentService = paymentIntentService;
    this.chargeMapper = chargeMapper;
  }

  @Override
  public Mono<ChargeResponse> create(String paymentIntentId, ChargeRequest chargeRequest) {
    UUID paymentIntentUUID = UUID.fromString(paymentIntentId);
    return paymentIntentService.findById(paymentIntentUUID).flatMap(paymentIntent -> {
      Charge charge = chargeMapper.toEntity(chargeRequest);
      charge.setPaymentIntentId(UUID.fromString(paymentIntent.id()));
      charge.setCapturedAt(Instant.now());
      return chargeRepository.save(charge).map(chargeMapper::toDto)
          .doOnSuccess(success -> {
            paymentIntentService.updateStatus(success.id(), 3);
          });
    });
  }

}
