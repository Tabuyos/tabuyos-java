package com.tabuyos.java.practice.p8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Tabuyos
 * @Time 3/1/20 11:35 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class SingleThreadDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
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
