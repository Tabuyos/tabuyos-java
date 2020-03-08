package com.tabuyos.java.practice.p6;

import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 2/29/20 8:25 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class Demo1 {

    private MyLock myLock = new MyLock();

    private int m = 0;

    public int next() {
        myLock.lock();
        try {
            return m++;
        } finally {
            myLock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo1 demo = new Demo1();
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            threads[i].start();
        }
    }
}
