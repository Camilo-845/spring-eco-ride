package com.example.payment_service.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, ServerWebExchange exchange) {
    ApiError apiError = ApiError.builder()
        .status(HttpStatus.NOT_FOUND)
        .error("Not Found")
        .message(ex.getMessage())
        .path(exchange.getRequest().getURI().getPath())
        .timestamp(Instant.now())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGoblaException(Exception ex, ServerWebExchange exchange) {
    ApiError apiError = ApiError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .error("Internal Server Error")
        .message(ex.getMessage())
        .path(exchange.getRequest().getURI().getPath())
        .timestamp(Instant.now())
        .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
  }

}
