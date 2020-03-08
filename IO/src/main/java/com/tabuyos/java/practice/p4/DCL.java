package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 3:25 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class DCL {

    private static DCL instance = null;
    private DCL() {}

    public static DCL getInstance() {
        if (instance == null) {
            // 使用同步代码块的方式
            synchronized (DCL.class) {
                if (instance == null) {
                    instance = new DCL();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(DCL.getInstance());
            }).start();
        }
    }

}
