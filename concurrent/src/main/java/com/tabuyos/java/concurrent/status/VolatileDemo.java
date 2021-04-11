/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.concurrent.status;

import java.util.concurrent.TimeUnit;

/**
 * volatile demo
 *
 * @author tabuyos
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread"})
public class VolatileDemo {

  private static int count = 0;

  public static void main(String[] args) throws InterruptedException {
    Thread t1 =
        new Thread(
            () -> {
              System.out.println("1: " + count);
              count++;
            });
    Thread t2 =
        new Thread(
            () -> {
              System.out.println("2: " + count);
              count++;
            });
    t1.start();
    TimeUnit.SECONDS.sleep(1);
    t2.start();
    t1.join();
    t2.join();
    System.out.println(count);
  }
}
