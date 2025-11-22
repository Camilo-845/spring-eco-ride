
package com.example.payment_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.payment_service.dto.request.PaymentIntentRequest;
import com.example.payment_service.dto.response.PaymentIntentResponse;
import com.example.payment_service.model.PaymentIntent;

@Mapper(componentModel = "spring")
public interface PaymentIntentMapper {

  PaymentIntentResponse toDto(PaymentIntent paymentIntent);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "status", ignore = true)
  PaymentIntent toEntity(PaymentIntentRequest createPaymentIntent);
}
