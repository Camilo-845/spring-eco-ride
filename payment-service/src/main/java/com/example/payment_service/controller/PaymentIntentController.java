package com.example.payment_service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.dto.request.ChargeRequest;
import com.example.payment_service.dto.request.PaymentIntentRequest;
import com.example.payment_service.dto.request.RefundRequest;
import com.example.payment_service.dto.response.ChargeResponse;
import com.example.payment_service.dto.response.PaymentIntentResponse;
import com.example.payment_service.dto.response.RefundResponse;
import com.example.payment_service.service.ChargeService;
import com.example.payment_service.service.PaymentIntentService;
import com.example.payment_service.service.RefundService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentIntentController {

  private final PaymentIntentService paymentIntentService;
  private final ChargeService chargeService;
  private final RefundService refundServie;

  public PaymentIntentController(PaymentIntentService paymentIntentService, ChargeService chargeService,
      RefundService refundServie) {
    this.paymentIntentService = paymentIntentService;
    this.chargeService = chargeService;
    this.refundServie = refundServie;
  }

  @PostMapping("/intents")
  public Mono<PaymentIntentResponse> createPaymentIntent(@Valid @RequestBody PaymentIntentRequest request) {
    return paymentIntentService.create(request);
  }

  @PostMapping("/capture/{intentId}")
  public Mono<ChargeResponse> createCharge(@Valid @RequestBody ChargeRequest request, @PathVariable String intentId) {
    Mono<ChargeResponse> charge = chargeService.create(intentId, request);
    // Lanzar evento PaymentAuthorized se se confirmo el pago corretamente
    return charge;
  }

  @PostMapping("/refund/{chargeId}")
  public Mono<RefundResponse> createRefund(@Valid @RequestBody RefundRequest request, @PathVariable String chargeId) {
    return refundServie.create(chargeId, request);
  }

}
