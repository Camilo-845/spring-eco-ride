package com.ecoride.passengerservice.service;

import com.ecoride.passengerservice.dto.rating.RatingRequestDto;
import com.ecoride.passengerservice.dto.rating.RatingResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RatingService {
    Mono<RatingResponseDto> create(RatingRequestDto ratingRequestDto);
    Mono<RatingResponseDto> getById(String id);
    Flux<RatingResponseDto> getAll();
    Mono<RatingResponseDto> update(String id, RatingRequestDto ratingRequestDto);
    Flux<RatingResponseDto> getAllByFromId(String passengerId);
}
