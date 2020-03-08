package com.tabuyos.java.practice.p7;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Author Tabuyos
 * @Time 3/1/20 9:47 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class CollectionDemo1 {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<String> concurrentLinkedQueue = new ConcurrentLinkedDeque<>();
        // add date
        Thread[] add = new Thread[100];
        for (int i = 0; i < 100; i++) {
            add[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    concurrentLinkedQueue.add(Thread.currentThread().getName() + ":Element " + j);
                }
            });
            add[i].start();
            add[i].join();
        }
        System.out.println("Size: " + concurrentLinkedQueue.size());
        // remove data
        Thread[] poll = new Thread[100];
        for (int i = 0; i < 100; i++) {
            poll[i] = new Thread(()->{
                for (int j = 0; j < 5000; j++) {
                    concurrentLinkedQueue.pollLast();
                    concurrentLinkedQueue.pollFirst();
                }
            });
            poll[i].start();
            poll[i].join();
        }
        System.out.println("After poll size: " + concurrentLinkedQueue.size());
    }
}
