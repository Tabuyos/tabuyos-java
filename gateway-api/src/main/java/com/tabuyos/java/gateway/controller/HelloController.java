/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller
 *
 * @author tabuyos
 */
@RestController
public class HelloController {

  @GetMapping("hello")
  public String hello() {
    return "Hello tabuyos ss.";
  }

  @GetMapping("rest/hello")
  public String restHello() {
    return "Hello tabuyos (rest/hello).";
  }
}
