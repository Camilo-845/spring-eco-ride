package com.example.payment_service.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.payment_service.dto.request.RefundRequest;
import com.example.payment_service.dto.response.RefundResponse;
import com.example.payment_service.exceptions.ApiException;
import com.example.payment_service.mapper.RefundMapper;
import com.example.payment_service.model.Refund;
import com.example.payment_service.repository.ChargeRepository;
import com.example.payment_service.repository.RefundRepository;
import com.example.payment_service.service.RefundService;

import reactor.core.publisher.Mono;

@Service
public class RefundServiceImpl implements RefundService {
  private final RefundRepository refundRepository;
  private final ChargeRepository chargeRepository;
  private final RefundMapper refundMapper;

  public RefundServiceImpl(RefundRepository refundRepository, ChargeRepository chargeRepository,
      RefundMapper refundMapper) {
    this.refundRepository = refundRepository;
    this.chargeRepository = chargeRepository;
    this.refundMapper = refundMapper;
  }

  @Override
  public Mono<RefundResponse> create(String chargeId, RefundRequest refundRequest) {
    UUID chargeUUID = UUID.fromString(chargeId);
    return chargeRepository.findById(chargeUUID)
        .switchIfEmpty(Mono.error(new ApiException("charge not found")))
        .flatMap(charge -> {
          Refund refund = refundMapper.toEntity(refundRequest);
          refund.setChargeId(charge.getId());
          refund.setCreatedAt(Instant.now());
          return refundRepository.save(refund).map(refundMapper::toDto);
        });
  }

}
