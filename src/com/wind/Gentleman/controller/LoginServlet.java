package com.wind.Gentleman.controller;

import com.wind.Gentleman.domain.User;
import com.wind.Gentleman.service.UserService;
import com.wind.Gentleman.utils.MD5Utils;
import com.wind.Gentleman.utils.WebUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applocationContext.xml");
    private UserService userService =  (UserService)applicationContext.getBean("userService");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (WebUtils.isNull(email)){
            request.setAttribute("loginMsg","❗ 邮箱不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if (WebUtils.isNull(password)){
            request.setAttribute("loginMsg","❗ 密码不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if (!email.matches("\\w+@\\w+(\\.\\w+)+")){
            request.setAttribute("loginMsg","❗ 邮箱格式错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(MD5Utils.md5(password));
        try {
            user = userService.loginUser(user);
            Cookie cookie = new Cookie("email",email + "#" + MD5Utils.md5(password));
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        }catch (Exception e){
            request.setAttribute("loginMsg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        request.getSession().setAttribute("user",user);
        request.getRequestDispatcher(request.getContextPath()+"/").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}