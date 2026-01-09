package com.example.notification_service.messages.listeners;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.notification_service.dtos.events.NotificationRequestEvent;
import com.example.notification_service.models.Outbox;
import com.example.notification_service.services.OutboxService;

import io.r2dbc.postgresql.codec.Json;

@Configuration
public class NotificationRequestListener {

  private final OutboxService outboxService;

  public NotificationRequestListener(OutboxService outboxService) {
    this.outboxService = outboxService;
  }

  @Bean
  public Consumer<NotificationRequestEvent> notificationRequest() {

    return event -> {
      Outbox outbox = Outbox.builder()
          .templateId(event.templateId())
          .status(event.outboxStatus())
          .payload(Json.of(event.payload()))
          .recipient(event.recipient())
          .scheduledAt(event.scheduledAt())
          .build();
      outboxService.create(outbox).subscribe();
    };
  }

}
