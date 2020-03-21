package com.tabuyos.java.dao;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Tabuyos
 * @Time 2020/3/21 17:41
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class IndexDaoImpl implements IndexDao {
    @Override
    public void query() {
        System.out.println("query.");
    }

    @Override
    public Map<String, Date> query(String name, Date date) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        for (long i = 0; i < 5000000000L; i++) {
            if (i % 10000 == 0) {
                System.out.println(i);
            }
        }
        Map<String, Date> map = new HashMap<>();
        map.put(name, date);
        calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        return map;
    }
}
