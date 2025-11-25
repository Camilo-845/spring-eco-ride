package com.ecoride.passengerservice.service.impl;

import com.ecoride.passengerservice.client.TripRestClient;
import com.ecoride.passengerservice.dto.rating.RatingRequestDto;
import com.ecoride.passengerservice.dto.rating.RatingResponseDto;
import com.ecoride.passengerservice.exception.ResourceNotFoundException;
import com.ecoride.passengerservice.mapper.RatingMapper;
import com.ecoride.passengerservice.model.Rating;
import com.ecoride.passengerservice.repository.RatingRepository;
import com.ecoride.passengerservice.service.DriverService;
import com.ecoride.passengerservice.service.PassengerService;
import com.ecoride.passengerservice.service.RatingService;
import com.ecoride.passengerservice.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final TripRestClient tripRestClient;
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final PassengerService passengerService;
    private final DriverService driverService;

    @Override
    public Mono<RatingResponseDto> create(RatingRequestDto ratingRequestDto) {

        Mono<Void> checkTrip = tripRestClient.getTripById(ratingRequestDto.getTripId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Trip with id " + ratingRequestDto.getTripId() + " not found")))
                .then();

        Mono<Void> checkFrom = passengerService.getById(ratingRequestDto.getFromId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Passenger not found with id: " + ratingRequestDto.getFromId())))
                .then();

        Mono<Void> checkTo = driverService.getById(ratingRequestDto.getToId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Driver not found with id: " + ratingRequestDto.getToId())))
                .then();

        return Mono.when(checkTrip,checkFrom,checkTo)
                .then(Mono.defer(() -> {
                    Rating rating = ratingMapper.toEntity(ratingRequestDto);
                    return ratingRepository.save(rating)
                            .map(ratingMapper::toDto);
                }));
    }

    @Override
    public Mono<RatingResponseDto> getById(String id) {
        UUID uuid = UUIDUtils.toUUID(id);
        return ratingRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Rating not found with id: " + uuid)))
                .map(ratingMapper::toDto);
    }

    @Override
    public Flux<RatingResponseDto> getAll() {
        return ratingRepository.findAll().map(ratingMapper::toDto);
    }

    @Override
    public Mono<RatingResponseDto> update(String id, RatingRequestDto ratingRequestDto) {
        UUID uuid = UUIDUtils.toUUID(id);

        Mono<Void> checkFrom = passengerService.getById(ratingRequestDto.getFromId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                        "Passenger not found with id: " + ratingRequestDto.getFromId())))
                .then();

        Mono<Void> checkTo = driverService.getById(ratingRequestDto.getToId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                        "Driver not found with id: " + ratingRequestDto.getToId())))
                .then();

        return ratingRepository.findById(uuid)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                        "Rating not found with id: " + uuid)))
                .flatMap(existingRating ->
                        Mono.when(checkFrom, checkTo)
                                .then(Mono.defer(() -> {
                                    existingRating.setScore(ratingRequestDto.getScore());
                                    existingRating.setComment(ratingRequestDto.getComment());
                                    return ratingRepository.save(existingRating);
                                }))
                )
                .map(ratingMapper::toDto);
    }

    @Override
    public Flux<RatingResponseDto> getAllByFromId(String passengerId) {
        UUID uuid = UUIDUtils.toUUID(passengerId);
        return ratingRepository.findByFromId(uuid).map(ratingMapper::toDto);
    }
}
