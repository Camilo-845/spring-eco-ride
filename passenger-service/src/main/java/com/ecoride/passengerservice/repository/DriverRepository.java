package com.ecoride.passengerservice.repository;

import com.ecoride.passengerservice.model.Driver;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface DriverRepository extends ReactiveCrudRepository<Driver, UUID> {
}
