package com.tabuyos.java.start;

import com.tabuyos.java.learn.NIOLearn;

/**
 * @Author Tabuyos
 * @Time 2019/9/30 13:20
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class Start {
    public static void main(String[] args) {
        System.out.println("====================Tabuyos Starting====================");
        NIOLearn nioLearn = new NIOLearn();
        nioLearn.init();
        System.out.println("====================Tabuyos Stopping====================");
    }
}
