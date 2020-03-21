package com.tabuyos.java.dao;

/**
 * @Author Tabuyos
 * @Time 2020/3/20 23:36
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public interface UserDao {
    void query() throws Exception;
    void query(String name, String age) throws Exception;
}
