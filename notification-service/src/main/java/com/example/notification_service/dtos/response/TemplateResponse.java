package com.example.notification_service.dtos.response;

public record TemplateResponse(Long id, Long eventType, String subject, String body, Long channel) {
}
