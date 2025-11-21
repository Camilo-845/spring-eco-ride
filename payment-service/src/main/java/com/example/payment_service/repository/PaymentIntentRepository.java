package com.example.payment_service.repository;

import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.example.payment_service.model.PaymentIntent;

public interface PaymentIntentRepository extends ReactiveCrudRepository<PaymentIntent, UUID> {

}
