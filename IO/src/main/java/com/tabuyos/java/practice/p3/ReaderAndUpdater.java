package com.tabuyos.java.practice.p3;

import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 2/28/20 4:14 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class ReaderAndUpdater {
    private final static int MAX = 5;
    private static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (localValue != init_value) {
                    System.out.println("Reader: " + init_value);
                    localValue = init_value;
                }
            }
        }, "Reader").start();
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Updater: " + (++localValue));
                init_value = localValue;
            }
        }, "Updater").start();

    }
}
