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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/RechargeServlet")
public class RechargeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        UserService userService = (UserService) new ClassPathXmlApplicationContext("applocationContext.xml").getBean("userService");
        String email = request.getParameter("email");
        String stbalace = request.getParameter("balance");
        if (WebUtils.isNull(email)){
            request.setAttribute("reMsg","❗ 邮箱不能为空");
            request.getRequestDispatcher("/Recharge.jsp").forward(request,response);
            return;
        }
        if (WebUtils.isNull(stbalace)){
            request.setAttribute("reMsg","❗ 充值金额不能为空");
            request.getRequestDispatcher("/Recharge.jsp").forward(request,response);
            return;
        }
        if (!email.matches("\\w+@\\w+(\\.\\w+)+")){
            request.setAttribute("reMsg","❗ 邮箱格式不正确");
            request.getRequestDispatcher("/Recharge.jsp").forward(request,response);
            return;
        }
        int balance = -1;
        try{
            balance = Integer.parseInt(request.getParameter("balance"));
        }catch (Exception e){
            request.setAttribute("reMsg","❗ 充值金额请输入一个整数");
            request.getRequestDispatcher("/Recharge.jsp").forward(request,response);
            return;
        }

        HttpSession session = request.getSession(false);

        if (balance != -1){
            User user = new User();
            user.setEmail(email);
            user.setBalance(balance);
            if (userService.findEmailUser(user)){
                user = userService.updateUserInfo(user);
                if (session.getAttribute("user")!=null){
                    try {
                        user = userService.isUser(user,request);
                    } catch (Exception e) {
                        request.setAttribute("reMsg",e.getMessage());
                        request.getRequestDispatcher("/login.jsp").forward(request,response);
                        return;
                    }
                    request.getSession().setAttribute("user",user);
                }
            }else {
                request.setAttribute("reMsg","❗ 用户不存在");
                request.getRequestDispatcher("/Recharge.jsp").forward(request,response);
                return;
            }
            request.getRequestDispatcher(request.getContextPath() + "/").forward(request,response);
        }else {
            request.setAttribute("reMsg","❗ 服务器繁忙，请稍后重试");
            request.getRequestDispatcher("/Recharge.jsp").forward(request,response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}