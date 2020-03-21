package com.tabuyos.java.proxy5;

import com.tabuyos.java.dao.IndexDao;
import com.tabuyos.java.dao.IndexDaoImpl;
import com.tabuyos.java.proxy4.TestCustomHandler;

import java.util.Date;

/**
 * @Author Tabuyos
 * @Time 2020/3/21 14:27
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class TestProxy {
    public static void main(String[] args) {
        Class<?>[] classes = new Class[]{IndexDao.class};
        IndexDao indexDao  = (IndexDao) ProxyUtilTool.newInstance(classes, new TestCustomHandler(new IndexDaoImpl()));
//        indexDao.query();
        System.out.println(indexDao.query("Tabuyos", new Date()));
    }
}
