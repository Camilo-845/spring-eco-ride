package com.example.notification_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("outbox_status")
public class OutboxStatus {
  @Id
  private Long id;
  private String name;
}
