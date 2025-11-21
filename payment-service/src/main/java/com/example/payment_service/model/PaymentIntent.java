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
@Table("payment_intents")
public class PaymentIntent {

  @Id
  private UUID id;
  private String reservationId;
  private Double amount;
  private String currency;
  private PaymentStatus status;

  @CreatedDate
  @Column("createdAt")
  private Instant createdAt;

  @LastModifiedDate
  @Column("updatedAt")
  private Instant updatedAt;
}
