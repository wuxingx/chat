<%--
  Created by IntelliJ IDEA.
  User: wuxing
  Date: 2017-2-1
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
        function sm(smid) {
            window.open("${ pageContext.request.contextPath }/PriChatServlet/PCBySmid?smid=" + smid);
        }
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
            <div>
                <c:forEach var="s" items="${ requestScope.smList.list }">
                    <div style="height: 25px;width: 300px;cursor:pointer" onclick="sm('${ s.id }')">
                        与
                        &nbsp;
                        &nbsp;
                        <c:if test="${ s.userInfo1.user.id ne sessionScope.realUser.user.id }">
                            ${ s.userInfo1.nickName }
                        </c:if>
                        <c:if test="${ s.userInfo2 ne sessionScope.realUser.user.id }">
                            ${ s.userInfo2.nickName }
                        </c:if>
                        &nbsp;
                        &nbsp;
                        的私聊
                        <c:if test="${ s.state != null }">
                            <c:if test="${sessionScope.realUser.user.id eq s.state }">
                                <%--当前用户有未读消息--%>
                                (您有未读消息)
                            </c:if>
                            <c:if test="${sessionScope.realUser.user.id ne s.state }">
                                <%--目标用户有未读消息--%>
                                (对方还未查看您的消息)
                            </c:if>
                        </c:if>
                    </div>
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
        <div class="col-md-12"></div>

    </div>
    <div class="col-md-4">

    </div>
</div>
</body>
</html>
