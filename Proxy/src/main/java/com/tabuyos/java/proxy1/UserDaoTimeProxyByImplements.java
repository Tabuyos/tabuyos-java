package com.tabuyos.java.proxy1;

import com.tabuyos.java.dao.UserDao;

/**
 * @Author Tabuyos
 * @Time 2020/3/20 23:54
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class UserDaoTimeProxyByImplements implements UserDao {

    private UserDao userDao;

    public UserDaoTimeProxyByImplements(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void query() throws Exception{
        System.out.println("===============Tabuyos-Time===============");
        userDao.query();
    }

    @Override
    public void query(String name, String age) throws Exception{

    }
}
