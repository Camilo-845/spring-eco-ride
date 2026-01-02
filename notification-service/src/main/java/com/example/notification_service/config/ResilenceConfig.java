package com.example.notification_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Configuration
public class ResilenceConfig {

  @Bean
  public Retry notificationRetry() {
    RetryConfig config = RetryConfig.custom()
        .maxAttempts(3)
        .waitDuration(Duration.ofSeconds(2))
        .retryExceptions(IOException.class, TimeoutException.class)
        .build();
    return Retry.of("notificationRetry", config);
  }
}
