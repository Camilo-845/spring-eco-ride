package com.ecoride.passengerservice.controller;

import com.ecoride.passengerservice.dto.rating.RatingRequestDto;
import com.ecoride.passengerservice.dto.rating.RatingResponseDto;
import com.ecoride.passengerservice.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public Mono<RatingResponseDto> create(@RequestBody @Valid RatingRequestDto ratingRequestDto) {
        return ratingService.create(ratingRequestDto);
    }

    @GetMapping("/{id}")
    public Mono<RatingResponseDto> getById(@PathVariable String id) {
        return ratingService.getById(id);
    }

    @GetMapping
    public Flux<RatingResponseDto> getAll() {
        return ratingService.getAll();
    }

    @PutMapping("/{id}")
    public Mono<RatingResponseDto> update(@PathVariable String id,
                                          @RequestBody @Valid RatingRequestDto ratingRequestDto) {
        return ratingService.update(id, ratingRequestDto);
    }

    @GetMapping("/from/{passengerId}")
    public Flux<RatingResponseDto> getAllByPassenger(@PathVariable String passengerId) {
        return ratingService.getAllByFromId(passengerId);
    }

}
