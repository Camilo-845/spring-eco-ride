package com.example.notification_service.mappers;

import org.mapstruct.Mapper;

import com.example.notification_service.dtos.response.OutboxResponse;
import com.example.notification_service.models.Outbox;

import io.r2dbc.postgresql.codec.Json;

@Mapper(componentModel = "spring")
public interface OutboxMapper {
  OutboxResponse toDto(Outbox outbox);

  default String mapJsonToString(Json json) {
    return json != null ? json.asString() : null;
  }
}
