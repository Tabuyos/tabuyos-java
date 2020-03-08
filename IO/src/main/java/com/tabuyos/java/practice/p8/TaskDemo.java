package com.tabuyos.java.practice.p8;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 3/1/20 11:22 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class TaskDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
