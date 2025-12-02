package com.ecoride.passengerservice.service;

import com.ecoride.passengerservice.dto.driver.DriverRequestDto;
import com.ecoride.passengerservice.dto.driver.DriverResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DriverService {
  Mono<DriverResponseDto> create(DriverRequestDto driverRequestDto);

  Mono<DriverResponseDto> getById(String id);

  Mono<DriverResponseDto> getByPassengerId(String id);

  Flux<DriverResponseDto> getAll();

  Mono<DriverResponseDto> update(String id, DriverRequestDto driverRequestDto);

  Mono<Void> delete(String id);
}
