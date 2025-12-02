package com.example.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter; // ⬅️ ¡ESTA ES LA QUE FALTABA!
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverter() {
    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter());
  }

  private JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());
    return converter;
  }

  @Bean
  public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity http,
      ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverter) {

    http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(exchanges -> exchanges
            .pathMatchers(HttpMethod.POST, "/trips").hasAuthority("ROLE_DRIVER")
            .pathMatchers(HttpMethod.GET, "/trips").hasAnyAuthority("ROLE_DRIVER", "ROLE_PASSENGER")
            .pathMatchers(HttpMethod.POST, "/trips/{tripId}/reservations").hasAuthority("ROLE_PASSENGER")
            .anyExchange().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt
                .jwtAuthenticationConverter(reactiveJwtAuthenticationConverter)));

    return http.build();
  }
}
