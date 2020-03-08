package com.tabuyos.java.practice.p6;

import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 2/29/20 8:25 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class Demo {

    private int m = 0;

    public int next() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return m++;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            threads[i].start();
        }
    }
}
