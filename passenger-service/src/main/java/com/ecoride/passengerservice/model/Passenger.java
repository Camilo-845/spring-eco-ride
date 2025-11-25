package com.ecoride.passengerservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("passengers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    private UUID id;

    @Column("keycloak_sub")
    private String keycloakSub;

    private String name;
    private String email;

    @Column("rating_avg")
    private Double ratingAvg;

    @Column("created_at")
    private LocalDateTime createdAt;


}
