package com.tabuyos.java.dao;

import java.util.Date;
import java.util.Map;

/**
 * @Author Tabuyos
 * @Time 2020/3/21 14:54
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public interface IndexDao {
    void query();
    Map<String, Date> query(String name, Date date);
}
