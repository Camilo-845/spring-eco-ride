package com.example.notification_service.exceptions;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public record ApiError(
    HttpStatus status,
    String error,
    String message,
    String path,
    Instant timestamp,
    List<String> details) {
}
