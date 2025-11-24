package com.ecoride.passengerservice.dto.trip;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripResponseDto {
    private Long id;
    private Long driverId;
    private String origin;
    private String destination;
    private LocalDateTime startTime;
    private Integer seatsTotal;
    private Integer seatsAvailable;
    private BigDecimal price;
    private String status;
}
