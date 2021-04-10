package com.tabuyos.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author Tabuyos
 * @Time 2020/3/21 10:31
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class TabuyosInvocationHandler implements InvocationHandler {

    Object target;

    public TabuyosInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy  代理对象
     * @param method 目标对象方法
     * @param args 目标对象方法参数
     * @return 目标对象执行结果
     * @throws Throwable 抛出异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy.");
        return method.invoke(target, args);
    }
}
