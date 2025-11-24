package com.ecoride.passengerservice.dto.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequestDto {
    @NotNull(message = "Passenger ID cannot be null")
    private String passengerId;

    @NotBlank(message = "License number cannot be blank")
    private String licenseNo;

    @NotBlank(message = "Car plate cannot be blank")
    private String carPlate;

    @Positive(message = "Seats offered must be greater than zero")
    private Integer seatsOffered;
}
