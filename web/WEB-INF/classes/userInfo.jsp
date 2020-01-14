<%@ page import="com.wind.Gentleman.domain.User" %><%--
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
        <form action="${pageContext.request.contextPath}/UpdateUserInfoServlet" method="POST">
            <h3 style="color:deeppink; font-weight: bold ; font-size:30px">我的用户信息</h3>
            <span style="color: red ;font-weight: bold; font-size:18px">${requestScope.updateMsg}</span>
            <hr>
            <table>

                <tr>
                    <td style="color:deeppink ; font-weight: bold; font-size:20px" class="card-title">昵称</td>
                </tr>
                <tr>
                    <td  class="card-title">
                        <input style="width: 220px;" type="text" name="rename" value="${param.rename==null?sessionScope.user.rename:param.rename}"/>

                    </td>
                </tr>
                <tr>
                    <td style="color:deeppink ; font-weight: bold; font-size:20px" class="card-title">账户邮箱</td>
                </tr>
                <tr>
                    <td  class="card-title">
                        <input style="width: 220px;" type="text" name="email" value="${param.email==null?sessionScope.user.email:param.email}"/>

                    </td>
                </tr>

                <tr>
                    <td style="color:deeppink ; font-weight: bold; font-size:20px" class="card-title" >🐏🐏币
                        (<a href="${pageContext.request.contextPath}/Recharge.jsp" >充值</a>)</td>
                </tr>
                <tr>
                    <td  class="card-title">
                        <input style="width: 220px;" type="text" name="balance" readonly="readonly" value="${sessionScope.user.balance}"/>

                    </td>

                </tr>

                <tr>
                    <td  style=" color:deeppink ; font-weight: bold; font-size:20px" class="card-title" class="card-title">VIP到期时间
                        (<a href="${pageContext.request.contextPath}/VipRun.jsp" >续费/开通</a>)
                    </td>
                </tr>
                <tr>
                    <td><input style="width: 220px;" type="text" name="date" readonly="readonly" value="${sessionScope.user.date==null?'您还不是VIP':sessionScope.user.date.toLocaleString()}"/></td>
                </tr>

                <tr>
                    <td  style="color:deeppink ; font-weight: bold; font-size:18px"><-( • ̀ω•́ )✧------------ヾ(´･ω･｀)ﾉ-></td>
                </tr>
                <tr>
                    <td><input type="submit" class="btn btn-primary" value="确认修改"/></td>
                </tr>


            </table>

        </form>

    </div>

</div>
<%@include file="_porn.jsp"%>
<div style="height: 45%"></div>
<hr>


<%@include file="_foot.jsp"%>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.0.0.js"></script>
</body>
</html>