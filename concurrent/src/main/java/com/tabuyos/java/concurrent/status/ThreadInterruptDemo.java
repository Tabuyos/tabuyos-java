/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.concurrent.status;

import java.util.concurrent.TimeUnit;

/**
 * Thread interrupt demo
 *
 * @author tabuyos
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread"})
public class ThreadInterruptDemo {

  public static void main(String[] args) throws InterruptedException {
    Thread thread =
        new Thread(
            () -> {
              while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                  System.out.println("before: " + interrupted);
                  Thread.interrupted();
                  System.out.println("after: " + Thread.currentThread().isInterrupted());
                }
              }
            });
    thread.start();
    TimeUnit.SECONDS.sleep(1);
    thread.interrupt();
  }
}
