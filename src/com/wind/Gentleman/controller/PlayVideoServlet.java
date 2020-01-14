package com.wind.Gentleman.controller;

import com.wind.Gentleman.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class PlayVideoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String porn = request.getParameter("porn");
        User user = (User)request.getSession(false).getAttribute("user");
        Long vipL = user.getDate()==null?0:user.getDate().getTime();
        if (Integer.parseInt(porn.split("n")[1])<=12){
            if (vipL>new Date().getTime()) {
                request.getRequestDispatcher(request.getContextPath() + "/PlayVideo.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher(request.getContextPath() + "/VipRun.jsp").forward(request,response);
            }
        }else {
            request.getRequestDispatcher(request.getContextPath() + "/PlayVideo.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}