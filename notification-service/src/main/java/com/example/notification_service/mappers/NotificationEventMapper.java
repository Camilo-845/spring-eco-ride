package com.example.notification_service.mappers;

import org.mapstruct.Mapper;

import com.example.notification_service.dtos.response.NotificationEventResponse;
import com.example.notification_service.models.NotificationEvent;

@Mapper(componentModel = "spring")
public interface NotificationEventMapper {
  NotificationEventResponse toDto(NotificationEvent notificationEvent);
}
