package com.ecoride.passengerservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import tools.jackson.databind.annotation.EnumNaming;

import java.time.Instant;
import java.util.UUID;

@Table("drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    @Id
    private UUID id;

    @Column("passenger_id")
    private UUID passengerId;

    @Column("license_no")
    private String licenseNo;

    @Column("verification_status")
    private VerificationStatus verificationStatus;

    @Column("is_active")
    private Boolean isActive;
}
