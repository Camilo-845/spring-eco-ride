package com.ecoride.passengerservice.controller;

import com.ecoride.passengerservice.dto.passenger.PassengerRequestDto;
import com.ecoride.passengerservice.dto.passenger.PassengerResponseDto;
import com.ecoride.passengerservice.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    public Mono<PassengerResponseDto> create(@Valid @RequestBody PassengerRequestDto PassengerRequestDto) {
        return passengerService.create(PassengerRequestDto);
    }

    @GetMapping
    public Flux<PassengerResponseDto> findAll() {
        return passengerService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<PassengerResponseDto> finById(@PathVariable String id) {
        return passengerService.getById(id);
    }

    @PutMapping("/{id}")
    public Mono<PassengerResponseDto> update(@PathVariable String id,@Valid @RequestBody PassengerRequestDto PassengerRequestDto) {
        return passengerService.update(id,PassengerRequestDto);
    }

    @GetMapping("/me/{keycloakSub}")
    public Mono<PassengerResponseDto> finByKeycloakSub(@PathVariable String keycloakSub) {
        return passengerService.getByKeycloakSub(keycloakSub);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return passengerService.delete(id);
    }

}
