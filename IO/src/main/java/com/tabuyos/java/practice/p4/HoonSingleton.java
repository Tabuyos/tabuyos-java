package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 2:57 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class HoonSingleton {

    private static HoonSingleton instance = null;
    private HoonSingleton() {}

    public static HoonSingleton getInstance() {
        try {
            if (instance == null) {
                // 模拟线程不安全
                Thread.sleep(1000);
                instance = new HoonSingleton();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HoonSingleton.getInstance());
            }).start();
        }
    }

}
