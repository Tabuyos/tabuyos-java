package com.tabuyos.java.proxy4;

import java.lang.reflect.Method;

public interface CustomInvocationHandler {
    Object invoke(Method method, Object[] args) throws Throwable;
}
