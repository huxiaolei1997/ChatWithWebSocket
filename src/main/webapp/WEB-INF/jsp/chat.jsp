<%--
  Created by IntelliJ IDEA.
  User: Mystery
  Date: 2018/6/4
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${user.userName}</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/chat.css" rel="stylesheet" type="text/css">
</head>
<body style="background-color:white;">
<div class="container">
    <div class="row" style="margin-top: 5%;">
        <div class="col-md-offset-2 col-md-8">
            <div class="alert alert-success" style="display: none;" role="alert">

            </div>
        </div>
        <div class="col-md-offset-2 col-md-6 chatbox">
            <div class="user-list" data-user-list-length="${fn:length(userList)}">
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
                <button type="button" id="loginOut" class="btn btn-primary">退出登录</button>
                <button type="button" id="sendMessage" style="float: right;margin-top: 3px;" class="btn btn-primary" data-to-user-id="">发送</button>
            </div>
        </div>
        <div class="col-md-2 settingsbox">
            <div id="settings">
                <span class="add-friend span-select">添加好友</span><span class="system-message">验证消息</span>
            </div>
            <div id="add-user" style="display: block;">
                <span>请输入您要添加为好友的用户名</span>
                <input type="text" style="font-size: 12px; width: 140px; display: inline-block;" class="form-control" id="userName" placeholder="请输入您要添加为好友的用户名" maxlength="20"/>
                <button type="button" class="btn btn-primary" id="find-user">查找</button>
                <div id="find-result-list">
                    <ul>
                    </ul>
                </div>
                <nav>
                    <ul class="pager">
                        <li><a href="#" class="prev">上一页</a></li>
                        <li><a href="#" class="next">下一页</a></li>
                    </ul>
                </nav>
            </div>
            <div id="system-message" style="display: none;">
                <div>
                    <ul>

                    </ul>
                </div>
                <nav>
                    <ul class="pager">
                        <li><a href="#" class="prev">上一页</a></li>
                        <li><a href="#" class="next">下一页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="static/js/chat.js" type="text/javascript"></script>
</body>
</html>
