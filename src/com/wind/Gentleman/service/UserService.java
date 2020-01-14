package com.wind.Gentleman.service;

import com.wind.Gentleman.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    //增加用户 返回true代表增加成功
    boolean addUser(User user);

    //根据邮箱查询用户是否存在 返回true代表查询到该用户
    boolean findEmailUser(User user);

    //用户登录 成功返回User代表可以登录
    User loginUser(User user) throws Exception;

    //修改用户信息
    User updateUserInfo(User user);

    //修改VIP套餐时间
    void updateVIP(User user);

    //返回应该重新存入session的User信息
    User isUser(User user, HttpServletRequest request) throws Exception;

    //扣除🐏🐏币
    public void delCurrency(User user,int curr) throws Exception;
}
