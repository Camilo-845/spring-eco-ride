package com.example.notification_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("templates")
public class Template {
  @Id
  private Long id;
  @Column("event_type")
  private long eventType;
  private String subject;
  private String body;
  private Long channel;
}
