package com.example.payment_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.payment_service.dto.request.RefundRequest;
import com.example.payment_service.dto.response.RefundResponse;
import com.example.payment_service.model.Refund;

@Mapper(componentModel = "spring")
public interface RefundMapper {

  RefundResponse toDto(Refund refund);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "chargeId", ignore = true)
  @Mapping(target = "reason", ignore = true)
  Refund toEntity(RefundRequest refundRequest);
}
