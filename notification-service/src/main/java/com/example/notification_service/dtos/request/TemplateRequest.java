package com.example.notification_service.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TemplateRequest(
    @NotNull(message = "eventType is required") Long eventType,
    @NotBlank(message = "subject is required") String subject,
    @NotBlank(message = "body is required") String body,
    @NotNull(message = "channel is required") Long channel) {
}
