package com.example.payment_service.dto.response;

public record ChargeResponse(String id, String paymentIntentId, String provider, String providerRef) {
}
