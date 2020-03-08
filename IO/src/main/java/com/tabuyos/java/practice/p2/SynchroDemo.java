package com.tabuyos.java.practice.p2;

import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 2/28/20 12:09 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class SynchroDemo {

    // decorate static method.
    public synchronized static void accessResources(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // decorate non-static method.
    public synchronized void accessResources1(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // decorate code block (this).
    public void accessResources2(){
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // decorate code block (class).
    public void accessResources3(){
        synchronized (SynchroDemo.class) { // ClassLoader class ---> 堆 Class 所有的对象。
            // 有Class对象的所有对象都共同使用这一个锁
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final SynchroDemo synchroDemo = new SynchroDemo();
        for (int i = 0; i < 5; i++) {
//            new Thread(SynchroDemo::accessResources).start();
//            new Thread(synchroDemo::accessResources1).start();
//            new Thread(synchroDemo::accessResources2).start();
            new Thread(synchroDemo::accessResources3).start();
        }
    }
}
