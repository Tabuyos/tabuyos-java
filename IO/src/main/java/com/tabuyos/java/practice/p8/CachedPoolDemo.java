package com.tabuyos.java.practice.p8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Tabuyos
 * @Time 3/1/20 11:33 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description non-fixed size
 */
public class CachedPoolDemo {

    public static void main(String[] args) {
        // create a fixed-size -5 thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        // create 10 task to thread pool
        for (int i = 0; i < 10; i++) {
            // create task
            Runnable runnable = new TaskDemo();
            // handle tasks to the thread pool for execution
            executorService.execute(runnable);
        }
        // shutdown thread pool
        executorService.shutdown();
    }
}
