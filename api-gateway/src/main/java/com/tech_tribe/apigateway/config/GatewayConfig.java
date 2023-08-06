package com.tech_tribe.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account_service", r -> r.path("/api/account/**")
                        .uri("lb://account-service"))
                .route("bill-service", r -> r.path("/api/biller/**")
                        .uri("lb://bill-service"))
                .route("payment-service", r -> r.path("/api/payment/**")
                        .uri("lb://payment-service"))
                .route("transaction-service", r -> r.path("/api/transaction/**")
                        .uri("lb://transaction-service"))
                .route("notification-service", r -> r.path("/api/notification/**")
                        .uri("lb://notification-service"))
                .build();
    }
}
