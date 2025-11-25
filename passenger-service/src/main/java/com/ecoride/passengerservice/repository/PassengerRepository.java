package com.ecoride.passengerservice.repository;

import com.ecoride.passengerservice.model.Passenger;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PassengerRepository extends ReactiveCrudRepository<Passenger, UUID> {

    Mono<Passenger> findByKeycloakSub(String sub);

}
