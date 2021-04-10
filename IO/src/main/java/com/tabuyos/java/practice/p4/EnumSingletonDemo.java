package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 4:12 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class EnumSingletonDemo {

    private EnumSingletonDemo() {
    }

    private enum EnumHolder {


        /**
         *
         */
        INTERFACE;
        private EnumSingletonDemo instance;

        EnumHolder() {
            this.instance = new EnumSingletonDemo();
        }

//        private EnumSingletonDemo getInstance() {
//            return instance;
//        }

    }

    public static EnumSingletonDemo getInstance() {
        return EnumHolder.INTERFACE.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(EnumSingletonDemo.getInstance());
            }).start();
        }
    }

}
