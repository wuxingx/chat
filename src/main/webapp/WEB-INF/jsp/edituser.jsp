<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-2-1
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript">
        $(function () {
            $("#nickName").blur(function () {
                if ($(this).val().length < 2 || $(this).val().length > 7) {
                    $("#nicknamelabel").append("<span class='formtips onError'>昵称不能小于2个且不能大于7个字符</span>")
                }
            })
            $("form").submit(function(){
                $("input").trigger("blur");
                var errorLength = $(".onError").length;
                console.log(errorLength)
                //如果有长度就说明有错误信息
                if(errorLength > 0) {
                    return false;
                }
            });
        })
    </script>
</head>
<body>
<%@ include file="navigationBar.jsp"%>

<div class="row">
    <div class="col-md-4">
    </div>
    <div class="col-md-4">
        <div class="col-md-12"></div>
        <div class="col-md-12">
            <form method="post" action="${ pageContext.request.contextPath }/EditUserInfoServlet" enctype="multipart/form-data">
                <div style="height: 50px">
                    <label id="nickNamelabel">昵称:</label><br/>
                    <input type="text" name="nickName" id="nickName" required value="${sessionScope.realUser.nickName}">
                </div>
                <div style="height: 52px">
                    <label>头像:</label>
                    <input type="file" name="upload">
                </div>
                <div style="height: 50px">
                    <label>邮箱:</label>
                    <input type="email" name="email" required value="${sessionScope.realUser.email}">
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
