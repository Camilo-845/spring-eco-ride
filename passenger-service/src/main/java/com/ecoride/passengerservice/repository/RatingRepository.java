package com.ecoride.passengerservice.repository;

import com.ecoride.passengerservice.model.Rating;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface RatingRepository extends ReactiveCrudRepository<Rating, UUID> {

    Flux<Rating> findByFromId(UUID passengerId);

}
