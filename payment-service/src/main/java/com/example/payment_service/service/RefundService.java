package com.example.payment_service.service;

import com.example.payment_service.dto.request.RefundRequest;
import com.example.payment_service.dto.response.RefundResponse;

import reactor.core.publisher.Mono;

public interface RefundService {
  Mono<RefundResponse> create(String chargeId, RefundRequest refundRequest);
}
