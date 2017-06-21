<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2016-12-21
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
    $(function () {
        setInterval("checkState()",1000);
    })
    function checkState() {
        $.post("${ pageContext.request.contextPath }/PriChatServlet/checkState",function (data) {
            if(data != "") {
                $("#mes").html("<a href='${ pageContext.request.contextPath }/UserInfoServlet/smList?currPage=1'>您有新的未读消息</a>")
            }else {
                $("#mes").html("")
            }
        })
    }
</script>
<div style="height: 56px;">
</div>
<div class="row">
    <!--导航条标签,导航栏设置为缺省,导航栏设置反色-->
    <nav class="navbar navbar-fixed-top navbar-inverse">
        <!--固定宽度为上层的100%-->
        <div class="container">
            <!--导航栏头-->
            <div class="navbar-header ">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!--第一个按钮-->
                <%--如果登录了--%>
                <a class="navbar-brand" href="${ pageContext.request.contextPath }/ChatServlet/homePage">首页</a>
                <a class="navbar-brand" href="${ pageContext.request.contextPath }/UserInfoServlet/userPage">您好:${ sessionScope.realUser.nickName }</a>
                <span class="navbar-brand" id="mes"></span>
                <a class="navbar-brand" href="${ pageContext.request.contextPath }/ChatServlet/userExit">退出</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>