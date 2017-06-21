<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-2-1
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sessionScope.realUser.nickName}--修改密码</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/regsit.css">
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/regist.js"></script>
</head>
<body>
<%@ include file="navigationBar.jsp"%>

<div class="row">
    <div class="col-md-4">
    </div>
    <div class="col-md-4">
        <div class="col-md-12" style="height:100px;"></div>
        <div class="col-md-12">
            <form id="changeForm" method="post" action="${ pageContext.request.contextPath }/UserInfoServlet/changePW">
                ${ requestScope.msg }
                <div style="height: 50px">
                    <label id="oldpwlabel">原密码:</label><br/>
                    <input type="password" name="oldpw" id="oldpw">
                </div>
                <div style="height: 52px">
                    <label id="passwordlable">新密码:</label><br/>
                    <input type="password" name="password" id="password" >
                </div>
                <div style="height: 50px">
                    <label id="repasswordlable">确认新密码:</label><br/>
                    <input type="password" name="repassword" id="repassword" >
                </div>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>
        <div class="col-md-12"></div>

    </div>
    <div class="col-md-4">

    </div>
</div>
</body>
</html>
