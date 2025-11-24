package com.ecoride.passengerservice.dto.passenger;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerRequestDto {

    @NotBlank(message = "KeycloakSub is required")
    String keycloakSub;

    @NotBlank(message = "Name is required")
    String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    String email;
}
