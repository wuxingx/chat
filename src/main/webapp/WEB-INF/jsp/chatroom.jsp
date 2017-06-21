<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-1-28
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聊天室:${ sessionScope.chatRoom.name }</title>
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
    <%--左侧列表显示该聊天室内所有用户--%>
    <div class="col-md-2" id="userList">
    </div>
    <%--显示聊天内容和发送框--%>
    <div class="col-md-9 row" style="border: 2px solid gray">
        <div class="col-md-12" style="background-color: black">
            <span><center style="color: white"><h1>聊天室:${ sessionScope.chatRoom.name }</h1></center></span>
        </div>
        <div class="col-md-12" id="msg-div" style="background-color: white;border: 1px yellow; width: 100%; height: 70%; overflow-x: hidden; overflow-y: scroll;word-wrap:break-word;word-break:break-all;">
        </div>
        <div class="col-md-12" style="background: #c8e5bc; text-align: center;">
            <textarea rows="5" cols="80%" id="msg"></textarea>
            <button type="button" class="btn btn-default btn-lg" onclick="sendMessage()">发送</button>
            <a target="_blank" href="${ pageContext.request.contextPath }/ChatServlet/roomHist?cid=${ chatRoom.id }&currPage=1">
                <button type="button" class="btn btn-default btn-lg">聊天记录</button>
            </a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        setInterval("userList1()", 3000);
        setTimeout("userList1()", 1)

    });
    function userList1() {
        $.post("${ pageContext.request.contextPath }/ChatServlet/userList", {"cid": '${sessionScope.chatRoom.id}'}, function (data) {
            $("#userList").html("");
            $("#userList").append("<div style='background-color: white'><h4><b>本聊天室在线列表</b></h4></div>");
            for (var o in data) {
                $("#userList").append("<div style='border: 1px solid red;height: 52px;width: 200px;background-color: white'><img  width='50px' height='50px' src='${ pageContext.request.contextPath }/" + data[o].image + "'>" + "<span>" + data[o].nickName + "</span>" + '&nbsp;' + "<c:if test='${ sessionScope.realUser.vip.vip >= 3 }'>" +
                    "<span style='cursor:pointer' class='pull-right' onclick=\"ban("+
                    "\'"+ data[o].user.id +"')\">BAN</span></c:if>" + "<span style='cursor:pointer' class='pull-left center-block' onclick=\"sm("+"\'"+ data[o].user.id +"')\">私聊<span>" +"</div>")
            }
        }, "json");
    }
    function sm(uid) {
        //私聊
        window.open("${ pageContext.request.contextPath }/PriChatServlet/priChat?uid=" + uid)
    }

    function ban(uid) {
//          ban人的连接
        $.post("${ pageContext.request.contextPath }/ChatServlet/banUser", {"uid": uid}, function (data) {
            if (data == "1") {
                userList1();
            }
        })
    }

    window.onload = function () {
        // 绑定事件
        document.onkeydown = function (obj) {
            if (obj.keyCode == 13) { // enter 键
                sendMessage();
                return false; // 不显示回车效果
            }
        };
        setInterval("showMessage()", 1000);
    }
    function sendMessage() {
        var val = $("#msg").val();
        if (val == "") {
            alert("消息不能为空");
            return;
        }
        if(val.length >= 100) {
            alert("消息不能大于500个字符");
            return;
        }
        $.post("${ pageContext.request.contextPath }/ChatServlet/sendMsg", {"msg": val}, function () {
            $("#msg").val("");
        });
    }

    function showMessage() {
        $.post("${pageContext.request.contextPath}/ChatServlet/check",function (data) {
            if(data != "") {
                location.reload(true);
            }
        })
        $.post("${ pageContext.request.contextPath }/ChatServlet/showMsg", function (data) {
            var $msssg = $("#msg-div");
            var elementById = document.getElementById("msg-div");
            if (data != "0") {
                $msssg.html("");
                for (var o in data) {
                    $msssg.append("<p style='color: black;'>" + "<img width='50px' height='50px' src='${ pageContext.request.contextPath }/" + data[o].userInfo.image + "'>" + data[o].userInfo.nickName +"---"+ data[o].date + " : " + data[o].msg+"</p>");
                }
                elementById.scrollTop = elementById.scrollHeight;
            } else {
                $msssg.html("<h2 class='text-center'>暂无聊天记录</h2>");
            }
        }, "json");
    }


</script>
</html>
