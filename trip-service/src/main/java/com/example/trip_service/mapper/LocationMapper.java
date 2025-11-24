package com.example.trip_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.trip_service.controller.dto.request.LocationRequest;
import com.example.trip_service.controller.dto.response.LocationResponse;
import com.example.trip_service.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
  LocationResponse toDto(Location location);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  Location toEntity(LocationRequest LocationRequest);
}
