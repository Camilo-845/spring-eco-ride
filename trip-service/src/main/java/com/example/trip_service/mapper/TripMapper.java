package com.example.trip_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.trip_service.controller.dto.request.TripRequest;
import com.example.trip_service.controller.dto.response.TripResponse;
import com.example.trip_service.model.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {
  TripResponse toDto(Trip trip);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "driverId", ignore = true)
  @Mapping(target = "seatsAvailable", ignore = true)
  @Mapping(target = "id", ignore = true)
  Trip toEntity(TripRequest tripRequest);
}
