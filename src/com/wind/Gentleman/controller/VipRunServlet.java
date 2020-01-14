package com.wind.Gentleman.controller;

import com.wind.Gentleman.domain.User;
import com.wind.Gentleman.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/VipRunServlet")
public class VipRunServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        UserService userService = (UserService) new ClassPathXmlApplicationContext("applocationContext.xml").getBean("userService");

        String email = request.getParameter("email");
        if (!email.matches("\\w+@\\w+(\\.\\w+)+")){
            request.setAttribute("vipMsg","❗ 邮箱格式不正确");
            request.getRequestDispatcher("/VipRun.jsp").forward(request,response);
            return;
        }


        String vipLon = request.getParameter("vip");
        long l = 1000*60*60*24L;
        l = l*Long.parseLong(vipLon);
        Date date = new Date();
        date.setTime(l);

        User seUser = (User) request.getSession().getAttribute("user");

        User user = new User();
        user.setEmail(email);
        user.setDate(date);
        user.setBalance(seUser.getBalance());
        if (!userService.findEmailUser(user)){
            request.setAttribute("vipMsg","❗ 用户不存在");
            request.getRequestDispatcher("/VipRun.jsp").forward(request,response);
            return;
        }
        userService.updateVIP(user);
        try {
            userService.delCurrency(user,Integer.parseInt(vipLon));
        } catch (Exception e) {
            request.setAttribute("vipMsg",e.getMessage());
            request.getRequestDispatcher("/VipRun.jsp").forward(request,response);
            return;
        }
        userService.updateUserInfo(user);

        try {
            user = userService.isUser(user,request);
        } catch (Exception e) {
            request.setAttribute("vipMsg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }


        request.getSession().setAttribute("user",user);
        request.getRequestDispatcher(request.getContextPath()+"/").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}