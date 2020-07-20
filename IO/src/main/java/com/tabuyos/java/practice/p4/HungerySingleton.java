package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 2:38 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class HungerySingleton {

    // 加载的时候就产生的实例对象
    private static HungerySingleton instance = new HungerySingleton();

    private HungerySingleton() {
        System.out.println("init");
    }

    // 返回实例对象
    public static HungerySingleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HungerySingleton.getInstance());
            }).start();
        }
    }

}
