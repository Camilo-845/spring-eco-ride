package com.ecoride.passengerservice.controller;

import com.ecoride.passengerservice.dto.driver.DriverRequestDto;
import com.ecoride.passengerservice.dto.driver.DriverResponseDto;
import com.ecoride.passengerservice.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drivers-profile")
public class DriverController {

  private final DriverService driverService;

  @PostMapping
  public Mono<DriverResponseDto> create(
      @Valid @RequestBody DriverRequestDto dto) {
    return driverService.create(dto);
  }

  @GetMapping("/{id}")
  public Mono<DriverResponseDto> getById(@PathVariable String id) {
    return driverService.getById(id);
  }

  @GetMapping
  public Flux<DriverResponseDto> getAll() {
    return driverService.getAll();
  }

  @GetMapping("/passenger/{id}")
  public Mono<DriverResponseDto> getByPassengerId(@PathVariable String id) {
    return driverService.getByPassengerId(id);
  }

  @PutMapping("/{id}")
  public Mono<DriverResponseDto> update(
      @PathVariable String id,
      @Valid @RequestBody DriverRequestDto dto) {
    return driverService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable String id) {
    return driverService.delete(id);
  }

}
