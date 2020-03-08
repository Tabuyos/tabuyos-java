package com.tabuyos.java.practice.p1;

import java.util.concurrent.BlockingQueue;

/**
 * @Author Tabuyos
 * @Time 2/27/20 10:39 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class Producer implements Runnable {
    private final BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                blockingQueue.put(produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int produce(){
        return 1;
    }
}
