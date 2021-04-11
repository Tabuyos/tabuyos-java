/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock demo
 *
 * @author tabuyos
 */
public class LockDemo {

  /** 公平重入锁和非公平重入锁 */
  static Lock lock = new ReentrantLock();

  private static int count = 0;

  public static synchronized void increat() throws InterruptedException {
    lock.lock();
    try {
      count++;
    } finally {
      lock.unlock();
    }
  }
}
