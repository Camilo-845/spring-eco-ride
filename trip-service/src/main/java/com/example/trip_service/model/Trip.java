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
@Table(name = "trips")
public class Trip {
  @Id
  private UUID id;
  @Column("driver_id")
  private UUID driverId;
  private Integer origin;
  private Integer destination;
  private Instant start_time;
  @Column("seats_total")
  private Integer seatsTotal;
  @Column("seats_available")
  private Integer seatsAvailable;
  @Column("created_at")
  private Instant createdAt;
  @Column("updated_at")
  private Instant updatedAt;
}
