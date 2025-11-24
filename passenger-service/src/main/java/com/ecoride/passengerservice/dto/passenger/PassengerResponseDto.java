package com.ecoride.passengerservice.dto.passenger;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerResponseDto {

    private UUID id;
    private String keycloakSub;
    private String name;
    private String email;
    private Double ratingAvg;
    private LocalDateTime createdAt;

}
