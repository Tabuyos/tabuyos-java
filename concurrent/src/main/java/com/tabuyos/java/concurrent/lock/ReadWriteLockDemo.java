/*
 * Copyright 2020-2021 the Tabuyos.
 */
package com.tabuyos.java.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * read-write lock demo
 *
 * @author tabuyos
 */
public class ReadWriteLockDemo {

  private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
  private static final Map<String, Object> MAP = new HashMap<>();
  private static final Lock READ = LOCK.readLock();
  private static final Lock WRITE = LOCK.writeLock();

  public static Object get(String key) {
    READ.lock();
    try {
      return MAP.get(key);
    } finally {
      READ.unlock();
    }
  }

  public static void set(String key, Object value) {
    WRITE.lock();
    try {
      MAP.put(key, value);
    } finally {
      WRITE.unlock();
    }
  }
}
