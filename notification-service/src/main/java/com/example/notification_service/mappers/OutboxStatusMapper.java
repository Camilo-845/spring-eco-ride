package com.example.notification_service.mappers;

import org.mapstruct.Mapper;

import com.example.notification_service.dtos.response.OutBoxStatusResponse;
import com.example.notification_service.models.OutboxStatus;

@Mapper(componentModel = "spring")
public interface OutboxStatusMapper {
  OutBoxStatusResponse toDto(OutboxStatus outboxStatus);
}
