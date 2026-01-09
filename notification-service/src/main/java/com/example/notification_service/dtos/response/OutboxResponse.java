package com.example.notification_service.dtos.response;

import java.time.Instant;

public record OutboxResponse(Long id, String payload, Long status, Long templateId, Integer retries,
    Instant createdAt, Instant sentAt, Instant scheduledAt) {
}
