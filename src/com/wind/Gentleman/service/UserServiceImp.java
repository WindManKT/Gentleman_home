package com.wind.Gentleman.service;

import com.wind.Gentleman.dao.UserDao;
import com.wind.Gentleman.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao =null;

    //添加用户
    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    //根据邮箱查询用户是否存在
    @Override
    public boolean findEmailUser(User user) {
        return userDao.findByEmail(user);
    }

    //用户登录，返回Session里所需要的User
    @Override
    public User loginUser(User user) throws Exception {
        if (userDao.findByEmail(user)){
            if (user.getPassword().equals(userDao.findAllByEmail(user).getPassword())){
                return userDao.findAllByEmail(user);
            }else {
                throw new Exception("用户名或密码错误");
            }
        }else {
            throw new Exception("用户名或密码错误");
        }
    }

    //修改用户信息，返回修改完成后可以存入Session的用户信息
    @Override
    public User updateUserInfo(User user) {
        User upuser = userDao.findById(user).getId()==0?userDao.findAllByEmail(user):userDao.findById(user);
        user.setRename(user.getRename()==null?upuser.getRename():user.getRename());
        user.setEmail(user.getEmail()==null?upuser.getEmail():user.getEmail());
        user.setPassword(user.getPassword()==null?upuser.getPassword():user.getPassword());
        user.setBalance(user.getBalance()==0?upuser.getBalance():(user.getBalance()+upuser.getBalance()));
        user.setDate(user.getDate()==null?upuser.getDate():user.getDate());
        user.setId(user.getId()==0?upuser.getId():user.getId());
        return userDao.updateUserInfo(user);
    }

    //vip套餐
    public void updateVIP(User user){
        Date date = new Date();
        long l = userDao.findAllByEmail(user).getDate()==null || userDao.findAllByEmail(user).getDate().getTime()<date.getTime()?date.getTime():userDao.findAllByEmail(user).getDate().getTime();
        date.setTime(user.getDate().getTime() + l);
        user.setDate(date);
    }

    //充值VIP，扣除🐏🐏币
    public void delCurrency(User user,int curr) throws Exception {

        if (curr==30){
            if (user.getBalance()>=30){
                user.setBalance(-30);
            }else {
                throw new Exception("❗ 余额不足，请充值🐏🐏币");
            }

        }else if (curr == 90){
            if (user.getBalance()>=80){
                user.setBalance(-80);
            }else {
                throw new Exception("❗ 余额不足，请充值🐏🐏币");
            }

        }else if (curr == 365){
            if (user.getBalance()>=200){
                user.setBalance(-200);
            }else {
                throw new Exception("❗ 余额不足，请充值🐏🐏币");
            }

        }else {
            throw new Exception("❗ 系统繁忙，请稍后重试");
        }

    }

    //返回应该重新存入session的User信息
    public User isUser(User user, HttpServletRequest request) throws Exception {
        if (request.getSession(false)!=null && request.getSession(false).getAttribute("user")!=null){
            user = (User) request.getSession().getAttribute("user");
            user = userDao.findAllByEmail(user);
            return user;
        }else {
           throw  new Exception("❗ 系统异常，请重新登录");
        }
    }
}
