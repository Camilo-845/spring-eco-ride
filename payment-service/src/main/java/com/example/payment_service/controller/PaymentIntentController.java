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
  public Mono<ChargeResponse> createCharge(@PathVariable String intentId, @Valid @RequestBody ChargeRequest request) {
    return chargeService.create(intentId, request)
        .flatMap(chageConfirm -> {
          paymentIntentService.updateStatus(intentId, 3);
          // Lanzar evento PaymentAuthorized se se confirmo el pago corretamente

          return Mono.just(chageConfirm);
        });
  }

  @PostMapping("/refund/{chargeId}")
  public Mono<RefundResponse> createRefund(@PathVariable String chargeId, @Valid @RequestBody RefundRequest request) {
    return refundServie.create(chargeId, request);
  }

}
