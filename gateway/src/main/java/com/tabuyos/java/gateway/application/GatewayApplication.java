/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * spring application startup class
 *
 * @author tabuyos
 */
@SpringBootApplication(scanBasePackages = {"com.tabuyos.java.gateway"})
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator kiteRouteLocator(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route("userRouter",
            p -> p.path("/custom/{segment}/rest")
                .filters(
                    f ->	f.addResponseHeader("X-CustomerHeader", "kite").setPath("/{segment}"))
                .uri("http://127.0.0.1:8229"))
        .build();
  }
}
