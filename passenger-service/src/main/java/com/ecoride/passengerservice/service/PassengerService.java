package com.ecoride.passengerservice.service;

import com.ecoride.passengerservice.dto.passenger.PassengerRequestDto;
import com.ecoride.passengerservice.dto.passenger.PassengerResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PassengerService {

    Mono<PassengerResponseDto> create(PassengerRequestDto passengerRequestDto);
    Mono<PassengerResponseDto> getById(String id);
    Mono<PassengerResponseDto> getByKeycloakSub(String sub);
    Flux<PassengerResponseDto> getAll();
    Mono<PassengerResponseDto> update(String id, PassengerRequestDto passengerRequestDto);
    Mono<Void> delete(String id);
}
