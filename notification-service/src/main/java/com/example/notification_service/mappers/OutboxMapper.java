package com.example.notification_service.mappers;

import org.mapstruct.Mapper;

import com.example.notification_service.dtos.response.OutboxResponse;
import com.example.notification_service.models.Outbox;

@Mapper(componentModel = "spring")
public interface OutboxMapper {
  OutboxResponse toDto(Outbox outbox);

}
