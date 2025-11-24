package com.example.trip_service.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table(name = "locations")
public class Location {
  @Id
  private Integer id;
  private String name;
  @Column("type_id")
  private Integer type;
  @Column("created_at")
  private Instant createdAt;
  @Column("updated_at")
  private Instant updatedAt;
}
