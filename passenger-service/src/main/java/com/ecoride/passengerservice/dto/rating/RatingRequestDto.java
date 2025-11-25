package com.ecoride.passengerservice.dto.rating;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingRequestDto {

    @NotNull(message = "Trip ID must not be null")
    private String tripId;

    @NotNull(message = "From ID must not be null")
    private String fromId;

    @NotNull(message = "To ID must not be null")
    private String toId;

    @NotNull(message = "Score must not be null")
    @DecimalMin(value = "1.0", message = "Score must be greater than 0")
    private Double score;

    @NotBlank(message = "Comment must not be blank")
    private String comment;

}
