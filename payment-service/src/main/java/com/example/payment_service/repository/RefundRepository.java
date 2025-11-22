package com.example.payment_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.payment_service.model.Refund;

public interface RefundRepository extends ReactiveCrudRepository<Refund, UUID> {

}
