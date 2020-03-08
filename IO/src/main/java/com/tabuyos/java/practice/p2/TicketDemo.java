package com.tabuyos.java.practice.p2;

import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 2/28/20 12:08 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class TicketDemo implements Runnable {
    private int index = 1;
    private final int MAX = 5;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread().getName() + " call number: " + (index++));
        }
    }

    public static void main(String[] args) {
        TicketDemo ticketDemo = new TicketDemo();
        Thread t1 = new Thread(ticketDemo, "Thread-0");
        Thread t2 = new Thread(ticketDemo, "Thread-1");
        Thread t3 = new Thread(ticketDemo, "Thread-2");
        Thread t4 = new Thread(ticketDemo, "Thread-3");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
