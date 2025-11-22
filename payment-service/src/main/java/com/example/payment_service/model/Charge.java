package com.example.payment_service.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("charges")
public class Charge {
  @Id
  private UUID id;
  @Column("payment_intent_id")
  private UUID paymentIntentId;
  private String provider;
  @Column("provider_ref")
  private String providerRef;
  @CreatedDate
  @Column("captured_at")
  private Instant capturedAt;
}
