package com.ecoride.passengerservice.dto.rating;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingResponseDto {

    private UUID id;
    private UUID tripId;
    private UUID fromId;
    private UUID toId;
    private Double score;
    private String comment;

}
