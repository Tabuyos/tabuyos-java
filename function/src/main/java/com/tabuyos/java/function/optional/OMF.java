package com.tabuyos.java.function.optional;

import com.tabuyos.java.function.magic.NMF1;

/**
 * @Author Tabuyos
 * @Time 2020/12/20 13:59
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
@FunctionalInterface
public interface OMF<T> extends MF {
    void apply(T[] t);
}
