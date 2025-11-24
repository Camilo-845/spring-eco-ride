package com.ecoride.passengerservice.service.impl;

import com.ecoride.passengerservice.dto.passenger.PassengerRequestDto;
import com.ecoride.passengerservice.dto.passenger.PassengerResponseDto;
import com.ecoride.passengerservice.exception.ResourceNotFoundException;
import com.ecoride.passengerservice.mapper.PassengerMapper;
import com.ecoride.passengerservice.model.Passenger;
import com.ecoride.passengerservice.repository.PassengerRepository;
import com.ecoride.passengerservice.service.PassengerService;
import com.ecoride.passengerservice.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;


    @Override
    public Mono<PassengerResponseDto> create(PassengerRequestDto passengerRequestDto) {
        Passenger passenger = passengerMapper.toEntity(passengerRequestDto);
        passenger.setCreatedAt(LocalDateTime.now());
        return passengerRepository.save(passenger).map(passengerMapper::toDto);
    }

    @Override
    public Mono<PassengerResponseDto> getById(String id) {
        UUID uuid = UUIDUtils.toUUID(id);
        return passengerRepository.findById(uuid).
                switchIfEmpty(Mono.error(new ResourceNotFoundException("Passenger not found with id: " + uuid)))
                .map(passengerMapper::toDto);
    }

    @Override
    public Mono<PassengerResponseDto> getByKeycloakSub(String sub) {
        return passengerRepository.findByKeycloakSub(sub).
                switchIfEmpty(Mono.error(new ResourceNotFoundException("Passenger not found with keycloak: " + sub)))
                .map(passengerMapper::toDto);
    }

    @Override
    public Flux<PassengerResponseDto> getAll() {
        return passengerRepository.findAll().map(passengerMapper::toDto);
    }

    @Override
    public Mono<PassengerResponseDto> update(String id, PassengerRequestDto passengerRequestDto) {
        UUID uuid = UUIDUtils.toUUID(id);
        return passengerRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Passenger not found with id: " + uuid)))
                .flatMap(existingPassenger -> {
                    existingPassenger.setName(passengerRequestDto.getName());
                    existingPassenger.setEmail(passengerRequestDto.getEmail());
                    existingPassenger.setKeycloakSub(passengerRequestDto.getKeycloakSub());
                    return passengerRepository.save(existingPassenger);
                })
                .map(passengerMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        UUID uuid = UUIDUtils.toUUID(id);
        return passengerRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Passenger not found with id: " + uuid)))
                .flatMap(passenger -> passengerRepository.deleteById(uuid));

    }
}
