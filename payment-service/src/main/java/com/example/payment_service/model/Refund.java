package com.example.payment_service.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("refunds")
public class Refund {
  @Id
  private UUID id;
  @Column("charge_id")
  private UUID chargeId;
  private double amount;
  private String reason;
  @Column("created_at")
  private Instant createdAt;
}
