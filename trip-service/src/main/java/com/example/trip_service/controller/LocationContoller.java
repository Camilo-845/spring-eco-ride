package com.example.trip_service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trip_service.controller.dto.request.LocationRequest;
import com.example.trip_service.controller.dto.response.LocationResponse;
import com.example.trip_service.service.LocationService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/locations")
public class LocationContoller {

  private final LocationService locationService;

  public LocationContoller(LocationService locationService) {
    this.locationService = locationService;
  }

  @PostMapping
  public Mono<LocationResponse> createLocation(@Valid @RequestBody LocationRequest request) {
    return locationService.create(request);
  }

  @PutMapping("/{id}")
  public Mono<LocationResponse> updateLocation(@PathVariable Long id, @Valid @RequestBody LocationRequest request) {
    return locationService.update(id, request);
  }

}
