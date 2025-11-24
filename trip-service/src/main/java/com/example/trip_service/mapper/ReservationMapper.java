package com.example.trip_service.mapper;

import org.mapstruct.Mapper;

import com.example.trip_service.controller.dto.request.ReservationRequest;
import com.example.trip_service.controller.dto.response.ReservationResponse;
import com.example.trip_service.model.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
  ReservationResponse toDto(Reservation reservation);

  Reservation toEntity(ReservationRequest reservationRequest);
}
