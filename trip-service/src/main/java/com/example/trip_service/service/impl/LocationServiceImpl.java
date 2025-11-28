package com.example.trip_service.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.trip_service.controller.dto.request.LocationRequest;
import com.example.trip_service.controller.dto.response.LocationResponse;
import com.example.trip_service.exceptions.ResourceNotFoundException;
import com.example.trip_service.mapper.LocationMapper;
import com.example.trip_service.model.Location;
import com.example.trip_service.repository.LocationRepository;
import com.example.trip_service.service.LocationService;

import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;
  private final LocationMapper locationMapper;

  public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
    this.locationRepository = locationRepository;
    this.locationMapper = locationMapper;
  }

  @Override
  public Mono<LocationResponse> getById(Long id) {
    return locationRepository.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("location", "id", id)))
        .map(locationMapper::toDto);
  }

  @Override
  public Flux<LocationResponse> getAll() {
    return locationRepository.findAll().map(locationMapper::toDto);
  }

  @Override
  public Mono<LocationResponse> create(LocationRequest request) {
    Location location = locationMapper.toEntity(request);
    location.setCreatedAt(Instant.now());
    location.setUpdatedAt(Instant.now());

    return locationRepository.save(location).map(locationMapper::toDto);
  }

  @Override
  public Mono<LocationResponse> update(@NotNull Long id, LocationRequest request) {
    return getById(id)
        .flatMap(result -> {
          Location location = locationMapper.toEntity(request);
          return locationRepository.save(location).map(locationMapper::toDto);
        });
  }

}
