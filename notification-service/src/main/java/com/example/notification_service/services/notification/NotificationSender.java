package com.example.notification_service.services.notification;

public interface NotificationSender {
  void send(String message, String recipient, String subject);

}
