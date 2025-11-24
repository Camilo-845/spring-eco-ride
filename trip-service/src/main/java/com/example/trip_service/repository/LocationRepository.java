package com.example.trip_service.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.trip_service.model.Location;

public interface LocationRepository extends ReactiveCrudRepository<Location, Long> {

}
