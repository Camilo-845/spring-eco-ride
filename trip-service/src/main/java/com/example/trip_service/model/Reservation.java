package com.example.trip_service.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table("reservations")
public class Reservation {
  @Id
  private UUID id;
  @Column("trip_id")
  private UUID tripId;
  @Column("passager_id")
  private UUID passagerId;
  @Column("status_id")
  private Integer status;
  @Column("created_at")
  private Instant createdAt;
  @Column("updated_at")
  private Instant updatedAt;
}
