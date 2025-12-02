package com.ecoride.passengerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex,
      ServerWebExchange request) {
    ApiError apiError = ApiError.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.NOT_FOUND.value())
        .message(ex.getMessage())
        .errors(null).build();
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex,
      ServerWebExchange request) {
    Map<String, String> errors = ex.getBindingResult().getFieldErrors()
        .stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    ApiError apiError = ApiError.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .message("Validation failed")
        .errors(errors).build();
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ExternalServiceException.class)
  public ResponseEntity<ApiError> handleExternalServiceException(ExternalServiceException ex,
      ServerWebExchange request) {
    ApiError apiError = ApiError.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_GATEWAY.value())
        .message(ex.getMessage())
        .errors(null).build();
    return new ResponseEntity<>(apiError, HttpStatus.BAD_GATEWAY);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception ex, ServerWebExchange request) {
    ApiError apiError = ApiError.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("An unexpected error occurred")
        .errors(null).build();
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
