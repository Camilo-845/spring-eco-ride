package com.ecoride.passengerservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("drivers")
public class Driver {

    @Id
    private UUID id;

    @Column("passenger_id")
    private UUID passengerId;

    @Column("license_no")
    private String licenseNo;

    @Column("car_plate")
    private String carPlate;

    @Column("seats_offered")
    private Integer seatsOffered;

    @Column("verification_status")
    private VerificationStatus verificationStatus;

}
