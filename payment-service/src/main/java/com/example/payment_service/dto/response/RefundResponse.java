package com.example.payment_service.dto.response;

public record RefundResponse(String id, String chargeId, Double amount, String reason) {
}
