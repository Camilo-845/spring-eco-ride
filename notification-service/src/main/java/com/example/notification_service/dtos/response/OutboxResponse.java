package com.example.notification_service.dtos.response;

import java.time.Instant;

public record OutboxResponse(Long id, Long eventType, String payload, Long status, Long templateId, Integer retries,
    Instant createdAt, Instant sentAt, Instant scheduledAt) {
}
