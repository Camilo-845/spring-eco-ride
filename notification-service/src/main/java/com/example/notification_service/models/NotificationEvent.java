package com.example.notification_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table("notification_events")
public class NotificationEvent {
  @Id
  private Long id;
  @Column("event_type")
  private String eventType;
  private String description;
}
