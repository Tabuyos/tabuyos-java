package com.tabuyos.java.proxy2;

import com.tabuyos.java.dao.UserDaoImpl;

/**
 * @Author Tabuyos
 * @Time 2020/3/20 23:41
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description Separator
 */
public class UserDaoImplLogProxyByExtends extends UserDaoImpl {
    @Override
    public void query() throws Exception{
        System.out.println("===============Tabuyos-Log===============");
        super.query();
    }
}
