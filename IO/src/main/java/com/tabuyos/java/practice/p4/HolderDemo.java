package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 3:44 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class HolderDemo {

    private HolderDemo() {}

    // 通过内部类， 从而实现了懒加载， 同时也没有synchronized， 这是目前使用最广泛的一种方式。
    private static class Holder{
        private static HolderDemo instance = new HolderDemo();
    }

    public static HolderDemo getInstance() {
        return Holder.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HolderDemo.getInstance());
            }).start();
        }
    }

}
