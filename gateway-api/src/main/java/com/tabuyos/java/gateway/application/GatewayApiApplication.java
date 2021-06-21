/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring application startup class
 *
 * @author tabuyos
 */
@SpringBootApplication(scanBasePackages = {"com.tabuyos.java.gateway"})
public class GatewayApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApiApplication.class, args);
  }
}
