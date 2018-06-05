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
    <title>登录页面</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .user-list {
            float: left;
            height: 548px;
            width: 150px;
            border: 1px solid red;
            display: inline-block;
        }

        .chat {
            float: right;
            width: 448px;
            height: 350px;
            border: 1px solid blue;
            background-color: white;
            overflow: auto;
        }

        .input-text {
            width: 448px;
            height: 198px;
            border: 1px solid black;
            float: right;
        }

        .user {
            width: 148px;
            height: 45px;
            border: 1px solid black;
            display: block;
        }

        .avatar {
            border-radius: 50%;
            width: 40px;
            height:40px;
            margin-right: 15px;
        }

        /** 聊天气泡效果 **/
        .sender{
            clear:both;
        }

        .sender div:nth-of-type(1){
            float: left;
        }

        .sender div:nth-of-type(2){
            background-color: aquamarine;
            float: left;
            margin: 0 20px 10px 15px;
            padding: 10px 10px 10px 0px;
            border-radius:7px;
        }
        .receiver div:first-child img,
        .sender div:first-child img{
            width:50px;
            height: 50px;
        }

        .receiver{
            clear:both;
        }

        .receiver div:nth-child(1){
            float: right;
        }

        .receiver div:nth-of-type(2){
            float:right;
            background-color: gold;
            margin: 0 10px 10px 20px;
            padding: 10px 0px 10px 10px;
            border-radius:7px;
        }

        .left_triangle{
            height:0px;
            width:0px;
            border-width:8px;
            border-style:solid;
            border-color:transparent aquamarine transparent transparent;
            position: relative;
            left:-16px;
            top:3px;
        }

        .right_triangle{
            height:0px;
            width:0px;
            border-width:8px;
            border-style:solid;
            border-color:transparent transparent transparent gold;
            position: relative;
            right:-16px;
            top:3px;
        }
    </style>
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
                <%--<span class="user" data-user-id="">--%>
                    <%--<img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>张三</span>--%>
                <%--</span>--%>
                <%--<span class="user">--%>
                    <%--<img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>张三</span>--%>
                <%--</span>--%>
                <%--<span class="user">--%>
                    <%--<img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>张三</span>--%>
                <%--</span>--%>
                <%--<span class="user">--%>
                    <%--<img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>张三</span>--%>
                <%--</span>--%>
                <%--<span class="user">--%>
                    <%--<img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>张三</span>--%>
                <%--</span>--%>
                <%--<span class="user">--%>
                    <%--<img src="static/images/avatar.jpg" alt="avatar" class="avatar"><span>张三</span>--%>
                <%--</span>--%>
            </div>
            <div class="chat" data-user-id="${user_id}">
                <!-- left -->
                <div class="sender">
                    <div>
                        <img src="static/images/avatar.jpg">
                    </div>
                    <div>
                        <div class="left_triangle"></div>
                        <span> hello, man! </span>
                    </div>
                </div>
                <!-- Right -->
                <div class="receiver">
                    <div>
                        <img src="static/images/avatar.jpg">
                    </div>
                    <div>
                        <div class="right_triangle"></div>
                        <span> hello world </span>
                    </div>
                </div>
                <!-- left -->
                <div class="sender">
                    <div>
                        <img src="static/images/avatar.jpg">
                    </div>
                    <div>
                        <div class="left_triangle"></div>
                        <span> hello, man! </span>
                    </div>
                </div>
                <!-- Right -->
                <div class="receiver">
                    <div>
                        <img src="static/images/avatar.jpg">
                    </div>
                    <div>
                        <div class="right_triangle"></div>
                        <span> hello world </span>
                    </div>
                </div>
            </div>
            <div class="input-text">
                <textarea id="content" style="width: 446px; height: 150px;" class="form-control"></textarea>
                <button type="button" id="sendMessage" style="float: right;margin-top: 3px;" class="btn btn-primary">发送</button>
            </div>
            <%--<!-- 最外边框 -->--%>
            <%--<div style="margin: 20px auto; border: 1px solid blue; width: 300px; height: 500px;">--%>
                <%--<!-- 消息展示框 -->--%>
                <%--<div id="msg" style="width: 100%; height: 70%; border: 1px solid yellow;overflow: auto;"></div>--%>
                <%--<!-- 消息编辑框 -->--%>
                <%--<textarea id="tx" style="width: 100%; height: 20%;"></textarea>--%>
                <%--<!-- 消息发送按钮 -->--%>
                <%--<input type="text" name="dest_user_id" id="dest_user_id" placeholder="输入要发送信息用户的id">--%>
                <%--<button id="TXBTN" style="width: 100%; height: 8%;">发送数据</button>--%>
                <%--<button id="getMessage" style="width: 100%; height: 8%;">获取聊天记录</button>--%>
                <%--<a href="javascript:;" id="loginOut">退出登录</a>--%>
            <%--</div>--%>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function() {
        // var user_id = Math.round(Math.random() * 100);
        // console.log(user_id);
        // $.ajax({
        //     url: "login/" + user_id,
        //     type: "GET",
        //     dataType: "json",
        //     success: function (response) {
        //
        //     },
        //     error: function() {
        //
        //     }
        // });
        var websocket;
        setTimeout(function() {
            // 首先判断是否 支持 WebSocket
            if('WebSocket' in window) {
                websocket = new WebSocket("ws://localhost:8088/ChatWithWebSocket/websocket");
            } else if('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://localhost:8088/ChatWithWebSocket/websocket");
            } else {
                websocket = new SockJS("http://localhost:8088/ChatWithWebSocket/sockjs/websocket");
            }

            // 打开时
            websocket.onopen = function(evnt) {
                console.log("  websocket.onopen  ");
            };

            // 处理消息时
            websocket.onmessage = function(evnt) {
                $("#msg").append("<p>(<font color='red'>" + evnt.data + "</font>)</p>");
                console.log("  websocket.onmessage   ");
            };

            websocket.onerror = function(evnt) {
                console.log("  websocket.onerror  ");
            };
            websocket.onclose = function(evnt) {
                console.log("  websocket.onclose  ");
            };

            // 点击了发送消息按钮的响应事件
            $("#TXBTN").click(function(){
                // 获取消息内容
                var text = $("#tx").val();
                // 判断
                if(text == null || text == ""){
                    alert(" content  can not empty!!");
                    return false;
                }
                // 获取要发送信息的用户的id
                var dest_user_id = $("#dest_user_id").val();
                $.ajax({
                    url: "message/" + dest_user_id + "/" + text,
                    type: "GET",
                    dataType: "json",
                    success: function (response) {

                    },
                    error: function() {

                    }
                });
                // var msg = {
                //     msgContent: text,
                //     postsId: 1
                // };
                // 发送消息
                //websocket.send(JSON.stringify(msg));
            });
        }, 1000);

        $("#loginOut").click(function() {
            //websocket.close
            if (!websocket.closed) {
                websocket.close();
            }
            location.href = "Login";
        });

        $(".message").click(function() {
            // 获取当前和自己聊天的用户id
            var to_user_id = $(this).attr("data-user-list-id");
            // 获取当前登录的用户的用户id
            var login_user_id = $(".chat").attr("data-user-id");
            console.log("to_user_id = " + to_user_id);
            // 获取和当前用户的聊天记录
            $.ajax({
                url : "getMessageRecord",
                type: "GET",
                data: {
                    to_user_id : to_user_id
                },
                dataType: "json",
                success: function(response) {
                    console.log(response);
                    var dataHtmlRecord = "";
                    for (var i = 0; i < response.length; i++) {
                        if (response[i].to_user_id == to_user_id) {
                            console.log("right = " + response[i].to_user_id);
                            dataHtmlRecord += "<div class=\"receiver\">"
                                + "<div>" + "<img src=\"static/images/avatar.jpg\">"
                                + "</div>" + "<div>" + "<div class=\"right_triangle\"></div>"
                                + "<span>" + response[i].content + "</span>" + "</div>" + "</div>";
                            //$(".chat").append();
                        } else if (response[i].to_user_id == login_user_id) {
                            console.log("left = " + response[i].from_user_id);
                            dataHtmlRecord += "<div class=\"sender\">" + "<div>"
                                + "<img src=\"static/images/avatar.jpg\">"
                                + "</div>" + "<div>" + "<div class=\"left_triangle\"></div>"
                                + "<span>" + response[i].content + "</span>" + "</div>" + "</div>";
                           //$(".chat").append();
                        }
                    }
                    $(".chat").html(dataHtmlRecord);
                },
                error:function () {

                }
            });
        });

    });
</script>
</body>
</html>
