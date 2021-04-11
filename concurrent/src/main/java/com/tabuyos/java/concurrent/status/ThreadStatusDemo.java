/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.concurrent.status;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态
 *
 * @author tabuyos
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread"})
public class ThreadStatusDemo {

  public static void main(String[] args) {
    new Thread(
            () -> {
              while (true) {
                try {
                  TimeUnit.SECONDS.sleep(300);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            },
            "timed-waiting")
        .start();

    new Thread(
            () -> {
              while (true) {
                synchronized (ThreadStatusDemo.class) {
                  try {
                    ThreadStatusDemo.class.wait();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              }
            },
            "waiting")
        .start();

    new Thread(BlockDemo::new, "block-demo-0").start();
    new Thread(BlockDemo::new, "block-demo-1").start();
  }

  static class BlockDemo extends Thread {
    @Override
    public void run() {
      synchronized (BlockDemo.class) {
        while (true) {
          try {
            TimeUnit.SECONDS.sleep(300);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
