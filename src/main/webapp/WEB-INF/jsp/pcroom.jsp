<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-2-1
  Time: 1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>与:${ sessionScope.pcroom.userInfo2.nickName }&nbsp;聊天</title>
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
<div id="loginModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <h1 class="text-center text-primary">与:${ sessionScope.pcroom.userInfo2.nickName }&nbsp;聊天</h1>
            </div>
            <div style="word-wrap:break-word;word-break:break-all;">
                <div class="col-md-12" id="msg-div" style="background-color: white;border: 1px yellow; width: 100%; height: 70%; overflow-x: hidden; overflow-y: scroll;word-wrap:break-word;word-break:break-all;">
                </div>

            </div>
            <div class="col-md-12" style="background: #c8e5bc; text-align: center;">
                <textarea rows="5" cols="80%" id="msg"></textarea>
                <button type="button" class="btn btn-default btn-lg" onclick="sendMessage()">发送</button>
                <a target="_blank" href="${ pageContext.request.contextPath }/PriChatServlet/smpcHist?smid=${ sessionScope.pcroom.id }&currPage=1">
                    <button type="button" class="btn btn-default btn-lg">聊天记录</button>
                </a>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        setInterval("userList1()", 10000);
        setTimeout("userList1()", 1)

    });

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
        $.post("${ pageContext.request.contextPath }/PriChatServlet/sendMsg", {"msg": val}, function () {
            $("#msg").val("");
        });
    }

    function showMessage() {
        $.post("${pageContext.request.contextPath}/PriChatServlet/check",function (data) {
            if(data != "") {
                location.reload(true);
            }
        })
        $.post("${ pageContext.request.contextPath }/PriChatServlet/showMsg", function (data) {
            var $msssg = $("#msg-div");
            var elementById = document.getElementById("msg-div");
            if (data != "0") {
                $msssg.html("");
                for (var o in data) {
                    $msssg.append("<p style='color: black;'>" + "<img width='50px' height='50px' src='${ pageContext.request.contextPath }/" + data[o].sendUser.image + "'>" + data[o].sendUser.nickName +"---"+ data[o].date + " : " + data[o].msg+"</p>");
                }
                elementById.scrollTop = elementById.scrollHeight;
            } else {
                $msssg.html("<h2 class='text-center'>暂无聊天记录</h2>");
            }
        }, "json");
    }


</script>
</html>
