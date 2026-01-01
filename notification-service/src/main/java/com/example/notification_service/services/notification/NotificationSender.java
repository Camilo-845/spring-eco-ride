package com.example.notification_service.services.notification;

import reactor.core.publisher.Mono;

public interface NotificationSender {
  Mono<Void> send(String message, String recipient);
}
