<%--
  Created by IntelliJ IDEA.
  User: Nolose
  Date: 2019/11/20
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>绅士之家</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap-4.0.0.css" rel="stylesheet">
    <style type="text/css">
        body{
            background-size:cover;
        }


    </style>
</head>
<body background="${pageContext.request.contextPath}/images/clouds.jpg">
<%@include file="_head.jsp"%>
<hr>

<div style="text-align: center;">
    <video width="800" height="500" controls autoplay>
        <source src="video/${param.porn}.mp4" type="video/mp4">
        您的浏览器不支持该视频播放。
    </video>
</div>



<div style="height: 30%"></div>
<hr>


<%@include file="_foot.jsp"%>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.0.0.js"></script>
</body>
</html>