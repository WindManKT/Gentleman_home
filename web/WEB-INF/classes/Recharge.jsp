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

<div class="container mt-3" >
    <div class="col-md-4 pb-1 pb-md-0">
        <form action="/RechargeServlet" method="POST">
            <h3 style="color:deeppink; font-weight: bold ; font-size:30px">绅士账户充值</h3>
            <span style="color: red ;font-weight: bold; font-size:18px">${requestScope.reMsg}</span>
            <hr>
            <table>

                <tr>
                    <td style="color:deeppink ; font-weight: bold; font-size:20px" class="card-title">请输入您的邮箱账户</td>
                </tr>
                <tr>
                    <td  class="card-title"><input type="text" name="email" value="${param.email==null?sessionScope.user.email:param.email}"/></td>
                </tr>
                <tr>
                    <td  style="color:deeppink ; font-weight: bold; font-size:20px" class="card-title" class="card-title">请输入您的充值金额(RMB)</td>
                </tr>
                <tr>
                    <td><input type="text" name="balance" value="${param.balance}"/></td>
                </tr>

                <tr>
                    <td  style="color:deeppink ; font-weight: bold; font-size:18px"><-( • ̀ω•́ )✧------------ヾ(´･ω･｀)ﾉ-></td>
                </tr>
                <tr>
                    <td><input type="submit" class="btn btn-primary" value="确认充值"/></td>
                </tr>


            </table>

        </form>

    </div>

</div>
<%@include file="_porn.jsp"%>
<div style="height: 51%"></div>
<hr>


<%@include file="_foot.jsp"%>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.0.0.js"></script>
</body>
</html>