package com.example.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * Gateway filter that relays the JWT token from the incoming request
 * to downstream services by preserving the Authorization header.
 * This filter ensures that the JWT token is propagated to backend
 * microservices without requiring them to implement their own
 * authentication or authorization.
 */
@Component
public class TokenRelayGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String authorizationHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader != null) {
                ServerHttpRequest request = exchange.getRequest()
                        .mutate()
                        .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                        .build();
                return chain.filter(exchange.mutate().request(request).build());
            }

            return chain.filter(exchange);
        };
    }
}
