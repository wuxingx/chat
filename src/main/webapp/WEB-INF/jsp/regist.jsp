<%@ page import="utils.UUIDUtils" %><%--
  Created by IntelliJ IDEA.
  User: wuxingx
  Date: 2017-1-23
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/regsit.css">
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/regist.js"></script>
    <script type="text/javascript">
        function changeCode() {
            document.getElementById("codeImg").src="${ pageContext.request.contextPath }/CheckImgServlet?time=" + new Date().getTime();
        }
        // 异步校验验证码
        $(function () {
            $("#registbtn").click(function () {
                var code = $("#checkCode").val();
                $.post("${ pageContext.request.contextPath }/UserServlet/registCode",{"checkCode":code},function (data) {
                    if(data == null) {
                        $("#checkCodelabel").append("<span class='formtips onError'>验证码错误</span>");
                        changeCode();
                    }else {
                        $("#registFrom").submit();
                    }
                })
            });
        });

    </script>


</head>
<body style="background-image:url('${ pageContext.request.contextPath }/img/wallpaper-296618.jpg') ">
    <%
        String uuid = UUIDUtils.getUUID();
        pageContext.setAttribute(uuid,uuid);
    %>
<div id="loginModal" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="text-center text-primary">注册</h1>
            </div>
            <div>
                <form id="registFrom" action="${pageContext.request.contextPath }/UserServlet/regist" method="post" class="form col-md-12 center-block">
                    <input type="text" name="uuid" value="${ pageScope.uuid }" hidden>
                    <br/>
                    <div class="form-group">
                        <label class="form-group" id="usernamelabel">用户名</label>
                        <input type="text" class="form-control input-lg" name="username" id="username" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label class="form-group" id="passwordlabel">密码</label>
                        <input type="password" class="form-control input-lg" name="password" id="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label class="form-group" id="repasswordlabel">重复输入密码</label>
                        <input type="password" class="form-control input-lg" name="repassword" id="repassword" placeholder="重复输入密码">
                    </div>
                    <div class="form-group">
                        <label class="form-group" id="emaillabel">邮箱</label>
                        <input type="email" class="form-control input-lg" name="email" id="email" placeholder="邮箱">
                    </div>
                    <div class="form-group">
                        <label class="form-group" id="nickNamelabel">昵称</label>
                        <input type="text" class="form-control input-lg" name="nickName" id="nickName" placeholder="昵称">
                    </div>
                    <div class="form-group">
                        <label class="form-group" id="checkCodelabel">验证码</label>
                        <input type="text" class="form-control input-lg" name="checkCode" id="checkCode" placeholder="验证码">
                        <img id="codeImg" src="${ pageContext.request.contextPath }/CheckImgServlet" onclick="changeCode()">
                    </div>
                    <div class="form-group">
                        <button type="button" id="registbtn" name="registbtn" class="btn btn-primary btn-lg btn-block">注册</button>
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
