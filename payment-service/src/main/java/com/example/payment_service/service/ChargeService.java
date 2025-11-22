package com.example.payment_service.service;

import com.example.payment_service.dto.request.ChargeRequest;
import com.example.payment_service.dto.response.ChargeResponse;

import reactor.core.publisher.Mono;

public interface ChargeService {
  Mono<ChargeResponse> create(String paymentIntentId, ChargeRequest chargeRequest);
}
