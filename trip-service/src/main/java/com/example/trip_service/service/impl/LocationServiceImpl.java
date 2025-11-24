package com.example.trip_service.service.impl;

import org.springframework.stereotype.Service;

import com.example.trip_service.controller.dto.response.LocationResponse;
import com.example.trip_service.exceptions.ResourceNotFoundException;
import com.example.trip_service.mapper.LocationMapper;
import com.example.trip_service.repository.LocationRepository;
import com.example.trip_service.service.LocationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;
  private final LocationMapper locationMapper;

  public LocationServiceImpl(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
    this.locationMapper = null;
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

}
