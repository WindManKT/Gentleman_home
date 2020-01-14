<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nolose
  Date: 2019/11/20
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/bootstrap-4.0.0.css" rel="stylesheet">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">绅士之家</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">主页</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/freeAll.jsp">免费视频</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/videoAll.jsp">全部视频</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/vipAll.jsp">VIP专区</a>
                </li>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Recharge.jsp">充值</a>
                </li>

            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="请输入搜索内容" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
            </form>

        </div>
    </div>
    <%
        if (request.getSession().getAttribute("user")==null){
    %>
    <a class="nav-link disabled" href="${pageContext.request.contextPath}/login.jsp">登录</a>
    <a class="nav-link disabled" href="${pageContext.request.contextPath}/regist.jsp">注册</a>
    <% }else{ %>
    <a class="nav-link disabled" href="${pageContext.request.contextPath}/userInfo.jsp">欢迎用户${sessionScope.user.email}回来</a>
    <a class="nav-link disabled" href="${pageContext.request.contextPath}/LoginOutServlet">注销</a>

    <%} %>

</nav>