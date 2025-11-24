package com.ecoride.passengerservice.mapper;

import com.ecoride.passengerservice.dto.passenger.PassengerRequestDto;
import com.ecoride.passengerservice.dto.passenger.PassengerResponseDto;
import com.ecoride.passengerservice.model.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    Passenger toEntity(PassengerRequestDto passengerRequestDto);
    PassengerResponseDto toDto(Passenger passenger);

}
