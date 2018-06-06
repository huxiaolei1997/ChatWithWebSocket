<%--
  Created by IntelliJ IDEA.
  User: Mystery
  Date: 2018/6/4
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${user.userName}</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/chat.css" rel="stylesheet" type="text/css">
</head>
<body style="background-color:#00B4EF;">
<div class="container">
    <div class="row" style="margin-top: 8%;">
        <div class="col-md-offset-3 col-md-6" style="border: 1px solid black; width: 600px;height: 550px;padding: 0px; border-radius: 5px;">
            <div class="user-list">
                <c:forEach items="${userList}" var="user">
                    <span class="user message" data-user-list-id="${user.id}">
                        <img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>${user.userName}</span>
                    </span>
                </c:forEach>
            </div>
            <div class="chat" data-user-id="${user_id}">
                <!-- 存放聊天内容 -->
            </div>
            <div class="input-text">
                <textarea id="content" style="width: 446px; height: 150px;" class="form-control"></textarea>
                <button type="button" id="sendMessage" style="float: right;margin-top: 3px;" class="btn btn-primary" data-to-user-id="">发送</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="static/js/chat.js" type="text/javascript"></script>
</body>
</html>
