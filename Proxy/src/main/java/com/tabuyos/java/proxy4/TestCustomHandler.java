package com.tabuyos.java.proxy4;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TestCustomHandler implements CustomInvocationHandler {

    Object target;

    public TestCustomHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Method method, Object[] args) throws Throwable {
        System.out.println("====================Tabuyos-Before====================");
        long beginTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("Take: " + ((endTime - beginTime) / 1000) + "s");
        System.out.println("====================Tabuyos-After====================");
        return result;
    }
}
