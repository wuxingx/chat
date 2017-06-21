<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-1-5
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>提示信息</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <style type="text/css">
        /*div {
            border: 1px solid red;
        }*/
    </style>
    <script type="text/javascript" >
        var time = 60;
        window.onload=function () {
            setInterval("changeNum()",1000);
        }
        function changeNum() {
            if(time > 0) {
                time--;
            }else if(time == 0) {
                $("#send").html("<a href='${ pageContext.request.contextPath }/UserServlet/reSend?email=${ sessionScope.email }' onclick='cannotclick()'><b>重新发送</b></a>");
            }
            $("#time").html(time);
        }
        function cannotclick() {
            $("#send").html("重新发送");
        }
    </script>
</head>
<body>
<div>
    <h1>${ msg }</h1>
    <c:if test="${ empty sessionScope.email }">
        <h1>返回<a href="${ pageContext.request.contextPath }/UserServlet/loginPage"><b>首页</b></a></h1>
    </c:if>
    <c:if test="${ not empty sessionScope.email }">
        <h3>邮件已经发送至您的邮箱,如果没有收到邮件,<b id="time">60</b>秒后<span id="send">重新发送</span></h3>
        <h4>常用邮箱链接:</h4>
        <%--${ pageContext.request.contextPath }/UserServlet/reSend--%>
        <div>
            <ul>
                <li><a href="https://mail.qq.com/">QQ邮箱</a></li>
                <li><a href="http://mail.163.com/">网易邮箱</a></li>
                <li><a href="https://login.live.com/">Hotmail邮箱</a></li>
                <li><a href="https://gmail.google.com/">Google邮箱</a></li>
                <li><a href="http://www.foxmail.com/">Foxmail邮箱</a></li>
                <li><a href="https://www.icloud.com/">iCloud邮箱</a></li>
            </ul>
        </div>
        <h4>已经激活?点击<a href="${ pageContext.request.contextPath   }/UserServlet/loginPage">登录</a></h4>
    </c:if>
</div>



</body>
</html>
