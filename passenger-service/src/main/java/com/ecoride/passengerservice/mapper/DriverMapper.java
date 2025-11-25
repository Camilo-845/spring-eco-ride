package com.ecoride.passengerservice.mapper;

import com.ecoride.passengerservice.dto.driver.DriverRequestDto;
import com.ecoride.passengerservice.dto.driver.DriverResponseDto;
import com.ecoride.passengerservice.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    Driver toEntity(DriverRequestDto driverRequestDto);
    DriverResponseDto toDto(Driver driver);
}
