package com.tabuyos.java.function.magic;

/**
 * @Author Tabuyos
 * @Time 2020/12/19 21:48
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@FunctionalInterface
public interface RMF3<A, B, C, R> {
    R apply(A a, B b, C c);
}
