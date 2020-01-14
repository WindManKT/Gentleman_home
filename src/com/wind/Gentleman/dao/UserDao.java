package com.wind.Gentleman.dao;

import com.wind.Gentleman.domain.User;

public interface UserDao {

    //增加用户
    boolean addUser(User user);

    //根据用户邮箱查询用户是否存在
    boolean findByEmail(User user);

    //根据id查询用户信息
    User findAllByEmail(User user);

    //修改用户信息，返回可以存入Session的User
    User updateUserInfo(User user);

    //根据id查询User 用户信息
    User findById(User user);
}
