package com.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rota para Order Service
                .route("order_service", r -> r.path("/orders/**")
                        .uri("http://localhost:8081")) // porta do seu order-service
                // Rota para Product Service
                .route("product_service", r -> r.path("/products/**")
                        .uri("http://localhost:8082")) // porta do seu product-service
                // Rota para Notification Service
                .route("notification_service", r -> r.path("/notifications/**")
                        .uri("http://localhost:8083")) // porta do seu notification-service
                .build();
    }
}
