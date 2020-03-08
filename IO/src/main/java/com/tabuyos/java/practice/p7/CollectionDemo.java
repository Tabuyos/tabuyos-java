package com.tabuyos.java.practice.p7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tabuyos
 * @Time 3/1/20 9:34 AM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class CollectionDemo {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User(i, "User" + i);
            users.add(user);

        }
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if ("User6".equals(user.getName())) {
                users.remove(user);
            }
        }
    }
}
