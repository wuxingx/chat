<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-2-1
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>历史记录</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
    <script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body style="background-image:url('${ pageContext.request.contextPath }/img/wallpaper-296618.jpg')">
<%@ include file="navigationBar.jsp"%>
<div id="loginModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <h1 class="text-center text-primary">${sessionScope.pcroom.userInfo2.nickName }--历史记录</h1>
            </div>
            <div style="word-wrap:break-word;word-break:break-all;">
                <c:forEach var="r" items="${smpcHist.list}">
                    <p style='color: black;'>
                        <img width='50px' height='50px' src='${ pageContext.request.contextPath }/${ r.sendUser.image }'>
                            ${r.sendUser.nickName}---${ r.date }:${ r.msg }
                    </p>
                </c:forEach>
            </div>

            <div class="modal-footer">
                <div style="position: relative;top: 50%;right: 35%">
                    <ul class="pagination" style="text-align:center; margin-top:10px;">
                        <c:if test="${ empty smpcHist.list }">
                        </c:if>
                        <c:if test="${ not empty smpcHist.list }">
                            <c:if test="${ smpcHist.currPage != 1 }">
                                <%--最前页--%>
                                <li>
                                    <a href="${ pageContext.request.contextPath }/PriChatServlet/smpcHist?currPage=1&smid=${ sessionScope.pcroom.id}" aria-label="Previous"><span aria-hidden="true">&laquo;&laquo;</span></a>
                                </li>
                            </c:if>
                            <%--如果当前页是1,就不让向前一页--%>
                            <c:if test="${ smpcHist.currPage == 1 }">
                                <li class="disabled">
                                        <%--前一页,获取当前页减1传给servlet和当前分类的cid--%>
                                    <span aria-hidden="true">&laquo;</span>
                                </li>
                            </c:if>
                            <c:if test="${ smpcHist.currPage != 1 }">
                                <li>
                                        <%--前一页,获取当前页减1传给servlet和当前分类的cid--%>
                                    <a href="${ pageContext.request.contextPath }/PriChatServlet/smpcHist?currPage=${smpcHist.currPage-1}&smid=${sessionScope.pcroom.id}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                                </li>
                            </c:if>
                            <%--显示所有页数从1到总页数--%>
                            <c:forEach var="i" begin="1" end="${ smpcHist.totalPage }">
                                <%--如果当前页等于页数,就链接去掉--%>
                                <c:if test="${ smpcHist.currPage == i }">
                                    <li class="active"><a href="javascript:void(0)" >${ i }</a></li>
                                </c:if>
                                <%--否则就显示连接,传递当前页和分类id--%>
                                <c:if test="${ smpcHist.currPage != i }">
                                    <li><a href="${ pageContext.request.contextPath }/PriChatServlet/smpcHist?currPage=${i}&smid=${sessionScope.pcroom.id}">${ i }</a></li>
                                </c:if>

                            </c:forEach>
                            <%--显示最后页的按钮,如果当前页等于总页数,就不显示--%>
                            <c:if test="${ smpcHist.currPage == smpcHist.totalPage }">
                                <li class="disabled">
                                    <span aria-hidden="true">&raquo;</span>
                                </li>
                            </c:if>
                            <c:if test="${ smpcHist.currPage != smpcHist.totalPage }">

                                <li>
                                        <%--如果当前页不等于最后一页,就还能向下一页跳转 连接给当前页+1--%>
                                    <a href="${ pageContext.request.contextPath }/PriChatServlet/smpcHist?currPage=${smpcHist.currPage+1}&smid=${ sessionScope.pcroom.id}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${ smpcHist.currPage != smpcHist.totalPage }">
                                <%--最前页--%>
                                <li>
                                    <a href="${ pageContext.request.contextPath }/PriChatServlet/smpcHist?currPage=${ smpcHist.totalPage }&smid=${ sessionScope.pcroom.id}" aria-label="Previous"><span aria-hidden="true">&raquo;&raquo;</span></a>
                                </li>
                            </c:if>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
