package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 3:18 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class HoonSynSingletonDemo {

    private static HoonSynSingletonDemo instance = null;
    private HoonSynSingletonDemo() {}

    public static HoonSynSingletonDemo getInstance() {
        if (instance == null) {
            // 使用同步代码块的方式
            synchronized (HoonSynSingletonDemo.class) {
                instance = new HoonSynSingletonDemo();
            }
            // 也会多次实例化， 为什么呢？
            // if这段段被两个线程同时执行时。
            // 因此需要再判断一次， 这就是DCL（Double-Check-Locking
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HoonSynSingletonDemo.getInstance());
            }).start();
        }
    }

}
