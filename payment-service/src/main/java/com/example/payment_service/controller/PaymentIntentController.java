package com.example.payment_service.controller;

import java.util.UUID;

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
import com.example.payment_service.messages.producers.PaymentProducer;
import com.example.payment_service.dto.events.PaymentAuthorizedEvent;
import com.example.payment_service.dto.events.PaymentFailedEvent;
import com.example.payment_service.service.ChargeService;
import com.example.payment_service.service.PaymentIntentService;
import com.example.payment_service.service.RefundService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentIntentController {

  private final PaymentIntentService paymentIntentService;
  private final PaymentProducer paymentProducer;
  private final ChargeService chargeService;
  private final RefundService refundServie;

  public PaymentIntentController(PaymentIntentService paymentIntentService, ChargeService chargeService,
      RefundService refundServie, PaymentProducer paymentProducer) {
    this.paymentIntentService = paymentIntentService;
    this.paymentProducer = paymentProducer;
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
        .doOnSuccess(success -> {
          paymentIntentService.findById(UUID.fromString(intentId))
              .flatMap(paymentIntent -> {
                PaymentAuthorizedEvent event = PaymentAuthorizedEvent.builder()
                    .reservationId(paymentIntent.reservationId())
                    .paymentIntentId(paymentIntent.id())
                    .chargeId(success.id()).build();
                boolean sendEvent = paymentProducer.sendPaymentAuthorizedEvent(event);
                return Mono.just(paymentIntent);
              })
              .subscribe();
        })
        .doOnError(error -> {
          paymentIntentService.findById(UUID.fromString(intentId))
              .flatMap(paymentIntent -> {
                PaymentFailedEvent event = PaymentFailedEvent.builder()
                    .reservationId(paymentIntent.reservationId())
                    .reason(error.getMessage()).build();
                paymentProducer.sendPaymentFailedEvent(event);
                return Mono.just(paymentIntent);
              })
              .subscribe();
        });
  }

  @PostMapping("/refund/{chargeId}")
  public Mono<RefundResponse> createRefund(@PathVariable String chargeId, @Valid @RequestBody RefundRequest request) {
    return refundServie.create(chargeId, request);
  }

}
