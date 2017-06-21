<%--
  Created by IntelliJ IDEA.
  User: wuxingx
  Date: 2017-1-23
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${sessionScope.realUser.nickName}</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
        <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="navigationBar.jsp"%>

<div class="row">
    <div class="col-md-4">
        <div class="col-md-12"></div>
        <div class="col-md-12">
            <div style="height: 50px">
                <span>
                    <a href="${ pageContext.request.contextPath }/UserInfoServlet/editUserPage" >修改个人信息</a>
                </span>
            </div>
            <div style="height: 50px">
                <span>
                    <a href="${ pageContext.request.contextPath}/UserInfoServlet/changePWPage" >修改密码</a>
                </span>
            </div>
            <div style="height: 50px">
                <span>
                    <a href="${ pageContext.request.contextPath }/UserInfoServlet/smList?currPage=1" >查看所有私聊</a>
                </span>
            </div>
            <div style="height: 50px">
                <span>
                    <a href="#" >充值VIP</a>
                </span>
            </div>
        </div>
        <div class="col-md-12"></div>
    </div>
    <div class="col-md-4">
        <div class="col-md-12"></div>
        <div class="col-md-12">
            <div style="height: 50px">
                <label>昵称:</label>
                ${sessionScope.realUser.nickName}
            </div>
            <div style="height: 52px">
                <label>头像:</label>
                <img height="50px" width="50px" src="${ pageContext.request.contextPath }/${sessionScope.realUser.image}">
            </div>
            <div style="height: 50px">
                <label>邮箱:</label>
                ${sessionScope.realUser.email}
            </div>
            <div style="height: 50px">
                <label>vip等级:</label>
                ${sessionScope.realUser.vip.vip}
            </div>
            <div style="height: 50px">
                <label>聊天等级:</label>
                ${sessionScope.realUser.chatlvp.plv}
            </div>
        </div>
        <div class="col-md-12"></div>

    </div>
    <div class="col-md-4">

    </div>
</div>
</body>
</html>
