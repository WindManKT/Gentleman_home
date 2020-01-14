package com.wind.Gentleman;

import com.wind.Gentleman.dao.UserDao;
import com.wind.Gentleman.dao.UserDaoImp;
import com.wind.Gentleman.domain.User;
import com.wind.Gentleman.utils.JdbcUtil;
import com.wind.Gentleman.utils.MD5Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test {

    public static void main(String[] args) {

        /*UserDao userDao = new UserDaoImp();
        User user = new User();
        user.setEmail("1658162548@qq.com");
        user = userDao.findAllByEmail(user);
        System.out.println(user);*/
//        UserDao userDao = new UserDaoImp();
//        User user = new User();
//        user.setEmail("1658162548@qq.com");
//        user.setRename(user.getRename());
        System.out.println(Math.exp(1));

    }

}
