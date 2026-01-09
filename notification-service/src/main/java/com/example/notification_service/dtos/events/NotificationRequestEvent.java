package com.example.notification_service.dtos.events;

import java.time.Instant;

public record NotificationRequestEvent(
    Long templateId,
    Long outboxStatus,
    String payload,
    String recipient,
    Instant scheduledAt) {
}
