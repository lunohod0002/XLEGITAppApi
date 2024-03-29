package com.example.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("eclient", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://eclient"))
                .route("eclient", r -> r.path("/api/v1/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://eclient"))
                .route("eclient", r -> r.path("/api/v1/items/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://eclient"))
                .build();
    }
}