package com.tabuyos.java.test;

import com.tabuyos.java.dao.UserDao;
import com.tabuyos.java.dao.UserDaoImpl;
import com.tabuyos.java.proxy.TabuyosInvocationHandler;
import com.tabuyos.java.proxy3.ProxyUtilTool;
import com.tabuyos.java.proxy4.ProxyUtil;
import com.tabuyos.java.proxy4.TestCustomHandler;

import java.lang.reflect.Proxy;

/**
 * @Author Tabuyos
 * @Time 3/20/20 11:24 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class Test {

    public static void main(String[] args) {
        // Static Proxy
        // 1
//        UserDaoImpl userDao = new UserDaoImpl();
//        userDao.query();
        // 2
//        UserDaoImpl userDao = new UserDaoImplLogProxyByExtends();
//        userDao.query();
        // 3
//        UserDao targetObject = new UserDaoImpl();
//        UserDao proxyObject = new UserDaoLogProxyByImplements(targetObject);
//        proxyObject.query();
        // 4
//        UserDao targetObject = new UserDaoImpl();
//        UserDao proxyObjectTemp = new UserDaoLogProxyByImplements(targetObject);
//        UserDao proxyObject = new UserDaoTimeProxyByImplements(proxyObjectTemp);
//        proxyObject.query();
        // 5
//        UserDao targetObject = new UserDaoImpl();
//        UserDao proxyObjectTemp = new UserDaoTimeProxyByImplements(targetObject);
//        UserDao proxyObject = new UserDaoLogProxyByImplements(proxyObjectTemp);
//        proxyObject.query();

        // Dynamic Proxy by custom
        // 1
//        UserDao target = new UserDaoImpl();
//        UserDao proxy = (UserDao) ProxyUtilTool.newInstance(target);
//        assert proxy != null;
//        proxy.query();
//        proxy.query("Tabuyos");
        // 2
//        UserDao proxy = (UserDao) ProxyUtil.newInstance(UserDao.class, new TestCustomHandler(new UserDaoImpl()));
//        assert proxy != null;
//        try {
//            proxy.query();
//            proxy.query("Tabuyos", "12");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Dynamic Proxy by JDK
        // 1
        Class<?>[] classes = new Class[]{UserDao.class};
        UserDao proxy = (UserDao) Proxy.newProxyInstance(Test.class.getClassLoader(), classes, new TabuyosInvocationHandler(new UserDaoImpl()));
        try {
            proxy.query("Tabuyos", "13");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
