package com.example.payment_service.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.service.PaymentIntentService;
import com.example.payment_service.dto.request.PaymentIntentRequest;
import com.example.payment_service.dto.response.PaymentIntentResponse;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentIntentController {

  private final PaymentIntentService paymentIntentService;

  public PaymentIntentController(PaymentIntentService paymentIntentService) {
    this.paymentIntentService = paymentIntentService;
  }

  @PostMapping("/intents")
  public Mono<PaymentIntentResponse> createPaymentIntent(@Valid @RequestBody PaymentIntentRequest request) {
    return paymentIntentService.create(request);
  }

}
