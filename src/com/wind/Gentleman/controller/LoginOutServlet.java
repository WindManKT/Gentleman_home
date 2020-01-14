package com.wind.Gentleman.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if (request.getSession(false)!=null){
            request.getSession().invalidate();
            Cookie cookie = new Cookie("email","");
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        request.getRequestDispatcher(request.getContextPath()+"/").forward(request,response);
//        response.sendRedirect(request.getContextPath()+"/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}