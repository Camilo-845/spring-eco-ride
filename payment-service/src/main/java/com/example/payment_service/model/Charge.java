package com.example.payment_service.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
  private String paymentIntentId;
  private Double amount;
  private String currency;
  private ChargeStatus status;

  @CreatedDate
  @Column("createdAt")
  private Instant createdAt;
}
