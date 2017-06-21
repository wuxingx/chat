<%@ page import="java.util.Map" %>
<%@ page import="com.wuxing.chat.javaBean.ChatRoom" %>
<%@ page import="com.wuxing.chat.javaBean.UserInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-1-25
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    <script type="text/javascript">
        //无密码
        function joinRoom(id) {
            location.href="${ pageContext.request.contextPath }/ChatServlet/room?cid=" + id;
        }
        //有密码
        function joinRoomP(id) {
            var password = window.prompt("请输入密码");
            if(password != "" && password != null) {
                $.post("${ pageContext.request.contextPath }/ChatServlet/checkPassword",{"id":id,"password":password},function (data) {
                    if(data == "1") {
                        //说明密码正确
                        $("#cidin").val(id);
                        $("#passwordin").val(password);
                        $("#form1").submit();
                        <%--$.post("${ pageContext.request.contextPath }/ChatServlet/roomp",{"cid":id,"password":password});--%>
                    }else {
                        alert("密码错误");
                    }
                });
            }

        }
        function full() {
            alert("该房间已满,请选择其他房间")
        }
    </script>
</head>
<body style="background-image:url('${ pageContext.request.contextPath }/img/wallpaper-296618.jpg')">
<%@ include file="navigationBar.jsp"%>

    <div id="loginModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h1 class="text-center text-primary">聊天室列表</h1>
                </div>
                <div>
                    <table class="table table-hover table-striped" id="rooms">
                        <c:forEach var="r" items="${ requestScope.rooms }">
                            <tr>
                                <c:if test="${ r.key.password == null }">
                                    <c:if test="${ fn:length(r.value) >= 200}">
                                        <td class="text-center"  ondblclick="full()">
                                        <h1 id="${ r.key.id }">${ r.key.name }(${ fn:length(r.value) }/200)</h1>
                                        </td>
                                    </c:if>
                                    <c:if test="${ fn:length(r.value) < 200}">
                                        <td class="text-center"  ondblclick="joinRoom('${ r.key.id }')">
                                            <h1 id="${ r.key.id }">${ r.key.name }(${ fn:length(r.value) }/200)</h1>
                                        </td>
                                    </c:if>
                                </c:if>
                                <c:if test="${ r.key.password != null }">
                                    <c:if test="${ fn:length(r.value) >= 200}">
                                        <td class="text-center"  ondblclick="full()">
                                        <h1 class="glyphicon glyphicon-lock " id="${ r.key.id }">${ r.key.name }(${ fn:length(r.value) }/200)</h1>
                                        </td>
                                    </c:if>
                                    <c:if test="${ fn:length(r.value) < 200}">
                                        <td class="text-center"  ondblclick="joinRoomP('${ r.key.id }')">
                                        <h1 class="glyphicon glyphicon-lock " id="${ r.key.id }">${ r.key.name }(${ fn:length(r.value) }/200)</h1>
                                        </td>
                                    </c:if>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="modal-footer">

                </div>
                <div hidden>
                    <form action="${ pageContext.request.contextPath }/ChatServlet/roomp" method="post" id="form1">
                        <input type="text" name="cid" id="cidin">
                        <input type="password" name="password" id="passwordin">
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
