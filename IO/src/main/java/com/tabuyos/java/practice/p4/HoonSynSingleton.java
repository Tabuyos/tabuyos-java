package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 3:08 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description 使用 synchronize 来保证唯一性。
 */
public class HoonSynSingleton {

    private static HoonSynSingleton instance = null;
    private HoonSynSingleton() {}

    // 使用同步方法的方式
    public synchronized static HoonSynSingleton getInstance() {
        if (instance == null) {
            instance = new HoonSynSingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HoonSynSingleton.getInstance());
            }).start();
        }
    }

}
