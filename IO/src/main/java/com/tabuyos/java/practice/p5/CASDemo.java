package com.tabuyos.java.practice.p5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Tabuyos
 * @Time 2/29/20 4:47 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class CASDemo {

    private static volatile int m = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase1() {
        m++;
    }

    public static void increase2() {
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] tf1 = new Thread[20];
        for (int i = 0; i < 20; i++) {
            tf1[i] = new Thread(() -> {
                CASDemo.increase1();
            });
            tf1[i].start();
            // join 方法， 是一个加入的概念， 将当前的线程加入到group中， 从而线程具有了交互性
            tf1[i].join();
        }
        System.out.println(m);

        Thread[] tf2 = new Thread[20];
        for (int i = 0; i < 20; i++) {
            tf2[i] = new Thread(() -> {
                CASDemo.increase2();
            });
            tf2[i].start();
            tf2[i].join();
        }
        System.out.println(atomicInteger.get());
    }
}
