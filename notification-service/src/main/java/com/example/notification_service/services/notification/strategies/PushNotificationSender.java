package com.example.notification_service.services.notification.strategies;

import org.springframework.stereotype.Service;

import com.example.notification_service.services.notification.NotificationSender;

import reactor.core.publisher.Mono;

@Service
public class PushNotificationSender implements NotificationSender {

  @Override
  public Mono<Void> send(String message, String recipient) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'send'");
  }

}
