package com.example.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity http) {
    http
        .authorizeExchange(exchanges -> exchanges
            .pathMatchers(HttpMethod.POST, "/trips").hasAuthority("ROLE_DRIVER")
            .pathMatchers(HttpMethod.GET, "/trips").hasAnyAuthority("ROLE_DRIVER", "ROLE_PASSENGER")
            .pathMatchers(HttpMethod.POST, "/trips/{tripId}/reservations").hasAuthority("ROLE_PASSENGER")
            .anyExchange().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(Customizer.withDefaults()));

    return http.build();
  }
}
