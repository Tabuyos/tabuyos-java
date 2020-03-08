package com.tabuyos.java.practice.p8;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author Tabuyos
 * @Time 3/1/20 11:40 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class ScheduledDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        // create 10 task to thread pool
        for (int i = 0; i < 10; i++) {
            // create task
            Runnable runnable = new TaskDemo();
            // handle tasks to the thread pool for execution
            scheduledExecutorService.execute(runnable);
        }
        // shutdown thread pool
        scheduledExecutorService.shutdown();
    }
}
