<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-1-6
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>激活</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<body>

    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form class="form-horizontal" method="post" action="${ pageContext.request.contextPath }/UserServlet/activeEmail">
                <div class="form-group">
                    <label style="color: red;">${ msg }</label>
                </div>
                <div class="form-group">
                    <label>请输入用户名</label>
                    <input type="text" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <label>请输入的邮箱</label>
                    <input type="text" class="form-control" name="email">
                </div>
                <div>
                    <button type="submit" class="btn btn-default">提交</button>
                </div>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>

</body>
</html>