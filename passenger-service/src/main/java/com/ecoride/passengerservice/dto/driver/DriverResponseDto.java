package com.ecoride.passengerservice.dto.driver;

import com.ecoride.passengerservice.model.VerificationStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverResponseDto {
    private UUID id;
    private UUID passengerId;
    private String licenseNo;
    private String carPlate;
    private Integer seatsOffered;
    private VerificationStatus verificationStatus;
}
