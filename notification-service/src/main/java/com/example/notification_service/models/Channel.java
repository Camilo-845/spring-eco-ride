package com.example.notification_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table("channels")
public class Channel {
  @Id
  private Long id;
  private String name;
}
