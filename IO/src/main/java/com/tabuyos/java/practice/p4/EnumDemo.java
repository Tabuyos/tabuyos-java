package com.tabuyos.java.practice.p4;

/**
 * @Author Tabuyos
 * @Time 2/29/20 4:06 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public enum EnumDemo {

    A, B, C, D;

    public static void m1() {
        System.out.println("Method");
    }

    public static void main(String[] args) {
        A.m1();
        B.m1();
        C.m1();
        D.m1();
    }
}
