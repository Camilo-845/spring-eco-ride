package com.example.trip_service.mapper;

import org.mapstruct.Mapper;

import com.example.trip_service.controller.dto.request.TripRequest;
import com.example.trip_service.controller.dto.response.TripResponse;
import com.example.trip_service.model.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {
  TripResponse toDto(Trip trip);

  Trip toEntity(TripRequest tripRequest);
}
