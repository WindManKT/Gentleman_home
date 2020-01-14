package com.wind.Gentleman.controller;

import com.wind.Gentleman.domain.User;
import com.wind.Gentleman.service.UserService;
import com.wind.Gentleman.utils.MD5Utils;
import com.wind.Gentleman.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(value = "/RegistServlet")
public class RegistServlet extends HttpServlet {

    UserService userService;
    ApplicationContext applicationContext;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        applicationContext = new ClassPathXmlApplicationContext("applocationContext.xml");
        userService =(UserService) applicationContext.getBean("userService");

        String email = request.getParameter("email");
        String password =  request.getParameter("password");
        String password2 = request.getParameter("password2");
        String vcode = request.getParameter("vcode");
        User user = new User();

        if (WebUtils.isNull(email)){
            request.setAttribute("msg","❗ 邮箱不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtils.isNull(password)){
            request.setAttribute("msg","❗ 密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtils.isNull(password2)){
            request.setAttribute("msg","❗ 确认密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtils.isNull(vcode)){
            request.setAttribute("msg","❗ 验证码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (!email.matches("\\w+@\\w+(\\.\\w+)+")){
            request.setAttribute("msg","❗ 邮箱格式错误");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        if (!password.equals(password2)){
            request.setAttribute("msg","❗ 两次密码输入不一致");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")){
            request.setAttribute("msg","❗ 密码必须是8-16位的英文和数字组成");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        if (!vcode.equalsIgnoreCase((String)request.getSession().getAttribute("code"))){
            request.setAttribute("msg","❗ 验证码输入错误");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        user.setDate(null);
        user.setBalance(0);
        user.setEmail(email);
        user.setPassword(MD5Utils.md5(password));

        if (userService.findEmailUser(user)){
            request.setAttribute("msg","❗ 此邮箱已被注册");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        if (userService.addUser(user)) {
            try {
                user = userService.loginUser(user);
            } catch (Exception e) {
                request.setAttribute("msg",e.getMessage());
                request.getRequestDispatcher("/regist.jsp").forward(request,response);
                return;
            }

            request.getSession().setAttribute("user",user);
            Cookie cookie = new Cookie("email",email + "#" + MD5Utils.md5(password));
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
            request.getRequestDispatcher(request.getContextPath() + "/").forward(request, response);
        }else {
            request.setAttribute("msg","❗ 系统繁忙，请稍后重试");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}