package com.tabuyos.java.function.magic;

/**
 * @Author Tabuyos
 * @Time 2020/12/19 21:50
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@FunctionalInterface
public interface NMF2<A, B> {
    void apply(A a, B b);
}
