package com.tabuyos.java.practice.p7;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 3/1/20 9:18 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant"})
public class CarDemo {

    public static void main(String[] args) {
        // create semaphore
        Semaphore semaphore = new Semaphore(5);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                // require
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " is Okay.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // use
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // release
                try {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " is Leave.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Car[" + i + "]");
            threads[i].start();
        }

    }
}
