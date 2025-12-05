package com.example.notification_service.models;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table("outboxes")
public class Outbox {
  @Id
  private Long id;
  @Column("event_type")
  private Long eventType;
  private String payload;
  private Long status;
  @Column("created_at")
  private Instant createdAt;
  @Column("sent_at")
  private Instant sentAt;
  @Column("scheduled_at")
  private Instant scheduledAt;
  @Column("template_id")
  private Long templateId;
  private Integer retries;

}
