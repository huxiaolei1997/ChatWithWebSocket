<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<!-- 最外边框 -->
<div style="margin: 20px auto; border: 1px solid blue; width: 300px; height: 500px;">
    <!-- 消息展示框 -->
    <div id="msg" style="width: 100%; height: 70%; border: 1px solid yellow;overflow: auto;"></div>
    <!-- 消息编辑框 -->
    <textarea id="tx" style="width: 100%; height: 20%;"></textarea>
    <!-- 消息发送按钮 -->
    <input type="text" name="dest_user_id" id="dest_user_id" placeholder="输入要发送信息用户的id">
    <button id="TXBTN" style="width: 100%; height: 8%;">发送数据</button>
</div>
<script src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
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
        setTimeout(function() {
            var websocket;

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
    });
</script>
</body>
</html>