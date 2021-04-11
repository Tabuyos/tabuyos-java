/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.concurrent.status;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * interrupt thread
 *
 * @author tabuyos
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread"})
public class InterruptDemo {

  private static int count;

  public static void main(String[] args) throws InterruptedException {
    Thread thread =
        new Thread(
            () -> {
              while (!Thread.currentThread().isInterrupted()) {
                count++;
              }
              System.out.println(count);
            });
    thread.start();
    TimeUnit.SECONDS.sleep(1);
    thread.interrupt();
  }
}
