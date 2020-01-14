package com.wind.Gentleman.controller;


import com.wind.Gentleman.utils.GraphicHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码servlet
 */
@WebServlet(value = "/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
        //不使用缓存
        response.setDateHeader("Expires",-1);
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","nocache");

        HttpSession session = request.getSession();
        final int width = 100; // 图片宽度
        final int height = 30; // 图片高度
        final String imgType = "jpeg"; // 指定图片格式 (不是指MIME类型)
        final OutputStream output = response.getOutputStream(); // 获得可以向客户端返回图片的输出流
        String code = GraphicHelper.create(width, height, imgType, output);
        session.setAttribute("code", code);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}