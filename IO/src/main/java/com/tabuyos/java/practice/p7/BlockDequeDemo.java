package com.tabuyos.java.practice.p7;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 3/1/20 10:05 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class BlockDequeDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>(3);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    String str = new String(i + ":" + j);
                    try {
                        linkedBlockingDeque.put(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Client: " + str + (new Date()));
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String str = linkedBlockingDeque.take();
                System.out.println("main:take " + str);
                TimeUnit.SECONDS.sleep(3);
            }
        }
        System.out.println("end");
    }
}
