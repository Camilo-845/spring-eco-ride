package com.example.trip_service.mapper;

import org.mapstruct.Mapper;

import com.example.trip_service.controller.dto.response.LocationResponse;
import com.example.trip_service.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
  LocationResponse toDto(Location location);
}
