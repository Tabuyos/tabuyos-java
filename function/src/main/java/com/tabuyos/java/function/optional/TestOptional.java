package com.tabuyos.java.function.optional;

import com.tabuyos.java.function.magic.Lazy;
import com.tabuyos.java.function.magic.RMF2;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author Tabuyos
 * @Time 2020/12/19 20:27
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class TestOptional {

    public static RMF2<Integer, Integer, Integer> test() {
        return Integer::sum;
    }

    public static int compute(int count) {
        System.out.println("计算");
        return count;
    }

    public static Lazy<Integer> lazyCompute(int count) {
        System.out.println("计算1");
        return new Lazy<>(() -> compute(count));
    }

    public static void main(String[] args) {
//        eager
        Integer v1 = compute(1);
//        lazy
        Supplier<Integer> v2 = () -> compute(2);

        System.out.println(v1);
        System.out.println(v2.get());

        Lazy<Integer> lazy0 = new Lazy<>(() -> compute(3));

        System.out.println(lazy0.get());
        System.out.println(lazy0.get());
        System.out.println(lazy0.get());

        Lazy<Integer> lazy1 = Lazy.of(() -> compute(42));
        Lazy<Integer> map = lazy1.map(s -> compute(s + 13));
        Lazy<Integer> flatMap = map.flatMap(s -> lazyCompute(s + 15));
        Lazy<Optional<Integer>> filter = flatMap.filter(v -> v > 0);

        System.out.println(filter.get());
        System.out.println(filter.get());
        System.out.println(filter.get());
    }
}
