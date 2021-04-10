package com.tabuyos.java.dao;

/**
 * @Author Tabuyos
 * @Time 2020/3/20 23:36
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void query() throws Exception {
        System.out.println("query.");
    }

    @Override
    public void query(String name, String age) throws Exception {
        System.out.println(name);
        System.out.println(age);
    }
}
