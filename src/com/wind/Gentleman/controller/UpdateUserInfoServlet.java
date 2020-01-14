package com.wind.Gentleman.controller;

import com.wind.Gentleman.domain.User;
import com.wind.Gentleman.service.UserService;
import com.wind.Gentleman.utils.WebUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        UserService userService = (UserService) new ClassPathXmlApplicationContext("applocationContext.xml").getBean("userService");
        String rename = request.getParameter("rename");
        String email = request.getParameter("email");
        if (WebUtils.isNull(rename)){
            request.setAttribute("updateMsg","❗ 昵称不能为空");
            request.getRequestDispatcher("/userInfo.jsp").forward(request,response);
            return;
        }
        if (WebUtils.isNull(email)){
            request.setAttribute("updateMsg","❗ 邮箱不能为空");
            request.getRequestDispatcher("/userInfo.jsp").forward(request,response);
            return;
        }
        if (!email.matches("\\w+@\\w+(\\.\\w+)+")){
            request.setAttribute("updateMsg","❗ 邮箱格式不正确");
            request.getRequestDispatcher("/userInfo.jsp").forward(request,response);
            return;
        }
        User user =(User)request.getSession(false).getAttribute("user");
        boolean isThisEmail = !user.getEmail().equals(email);
        user.setEmail(email);
        if (isThisEmail && userService.findEmailUser(user)){
            request.setAttribute("updateMsg","❗ 邮箱已存在");
            request.getRequestDispatcher("/userInfo.jsp").forward(request,response);
            return;
        }
        user.setRename(rename);
        userService.updateUserInfo(user);
        try {
            user = userService.isUser(user,request);
        } catch (Exception e) {
            request.setAttribute("updateMsg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        request.getSession().setAttribute("user",user);
        request.getRequestDispatcher(request.getContextPath() + "/").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}