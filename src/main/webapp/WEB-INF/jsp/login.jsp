<%@ page import="utils.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-1-19
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聊天室</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <style>
        body {

            margin:0;
            padding:0;
        }
    </style>
    <script type="text/javascript">
       /* $(function () {
            $("form input").blur(function() {
                var $parent = $(this).parent();
                if($(this).is("#username")) {
                    if($(this).val() == "") {
                        //父元素(<td>)添加append:
                        $parent.append("</br><span class='formtips onError'>用户名不能为空</span>");
                    }else {
                        $("#usernamelabel").append("</br><span class='formtips onSuccess'>用户名输入正确</span>");

                    }
                }
            })
        })*/
    </script>
</head>
<body style="background-image:url('${ pageContext.request.contextPath }/img/wallpaper-296618.jpg') ">
    <%
        Cookie[] cookies = request.getCookies();
        Cookie autoWrite = CookieUtils.cookie(cookies, "autoWrite");
        if(autoWrite != null) {
            String username = autoWrite.getValue().split("#")[0];
            String password = autoWrite.getValue().split("#")[1];
            pageContext.setAttribute("username",username);
            pageContext.setAttribute("password",password);
        }

    %>
    <div id="loginModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h1 class="text-center text-primary">登录</h1>
                </div>
                <div>
                    <form action="${ pageContext.request.contextPath }/UserServlet/login" method="post" class="form col-md-12 center-block">
                        <br/>
                        ${ requestScope.msg }
                        <div class="form-group">
                            <input type="text" class="form-control input-lg" required name="username" placeholder="用户名" value="${ pageScope.username }">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control input-lg" required name="password" placeholder="登录密码" value="${ pageScope.password }">
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="autoLogin" value="true">自动登录
                            &nbsp;
                            <input type="checkbox" name="autoWrite" value="true">记住账号
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-lg btn-block">立刻登录</button>
                            <span><a href="#">找回密码</a></span>
                            <span><a href="${ pageContext.request.contextPath }/UserServlet/registPage" class="pull-right">注册</a></span>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
</body>
</html>
