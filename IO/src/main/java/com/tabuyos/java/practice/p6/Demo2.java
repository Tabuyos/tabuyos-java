package com.tabuyos.java.practice.p6;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Tabuyos
 * @Time 2/29/20 8:25 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description 不对请求锁进行修改的话， 不能实现可重入性， 因此， 当资源占有的时候， 不能再次加锁
 */
public class Demo2 {

    private MyLock myLock = new MyLock();
//    private ReentrantLock myLock = new ReentrantLock(); // JDK自带的， 已经实现了可重入性， 因此可以直接拿来使用就行了

    private int m = 0;

    public void a() {
        myLock.lock();
        System.out.println("a");
        b();
        myLock.unlock();
    }

    public void b() {
        myLock.lock();
        System.out.println("b");
        myLock.unlock();
    }

    // a和b中的lock是同一个锁

    public static void main(String[] args) {
        Demo2 demo = new Demo2();
        new Thread(() -> {
            demo.a();
        }).start();

    }
}
