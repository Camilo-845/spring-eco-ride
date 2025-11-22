package com.example.payment_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.payment_service.dto.request.ChargeRequest;
import com.example.payment_service.dto.response.ChargeResponse;
import com.example.payment_service.model.Charge;

@Mapper(componentModel = "spring")
public interface ChargeMapper {

  ChargeResponse toDto(Charge charge);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "capturedAt", ignore = true)
  Charge toEntity(ChargeRequest chargeRequest);
}
