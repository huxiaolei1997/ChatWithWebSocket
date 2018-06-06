$(function() {
    // 设置默认发送信息给第一个好友
    var to_user_id_default = $(".message").eq(0).attr("data-user-list-id");
    console.log("默认发送消息给第一个好友：" + to_user_id_default);
    $("#sendMessage").attr("data-to-user-id", to_user_id_default);
    // 默认获取和第一个好友的聊天记录
    getChatRecord(to_user_id_default);
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
            console.log("evet: " + evnt);
            console.log("收到了一条消息，消息内容是：" + evnt.data);
            //$("#msg").append("<p>(<font color='red'>" + evnt.data + "</font>)</p>");
            $(".chat").append("<div class=\"sender\">" + "<div>"
                + "<img src=\"static/images/avatar.jpg\">"
                + "</div>" + "<div>" + "<div class=\"left_triangle\"></div>"
                + "<span>" + evnt.data + "</span>" + "</div>" + "</div>");
            // 滑动滚动条到底部
            //console.log()
            // $(".chat").scrollTop =  $(".chat").scrollHeight;
            //document.getElementsByClassName("chat")[0].scrollIntoView(false);
            var chat = document.getElementsByClassName("chat")[0];
            chat.scrollTop = chat.scrollHeight;
            console.log("  websocket.onmessage   ");
        };

        websocket.onerror = function(evnt) {
            console.log("  websocket.onerror  ");
        };
        websocket.onclose = function(evnt) {
            console.log("  websocket.onclose  ");
        };

        // 按回车键发送消息给好友
        $(document).keydown(function(event) {
            if (event.keyCode == 13) {
                $("#sendMessage").click();
            }
        });

        // 点击发送按钮发送信息给好友
        $("#sendMessage").click(function(){
            // 获取消息内容
            var message_content = $("#content").val();
            // 判断
            if(message_content == null || message_content == ""){
                alert("message_content can not empty!!");
                return false;
            }
            // 获取收信人的id
            var to_user_id = $(this).attr("data-to-user-id");
            console.log("收信人id:" + to_user_id);
            // 获取发信人的id
            var from_user_id = $(".chat").attr("data-user-id");
            console.log("发信人id:" + from_user_id);
            // 发送时间
            var send_time = new Date();
            $.ajax({
                url: "message",
                type: "POST",
                data: JSON.stringify({
                    from_user_id : from_user_id,
                    to_user_id : to_user_id,
                    content: message_content,
                    send_time: send_time
                }),
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    $(".chat").append("<div class=\"receiver\">"
                        + "<div>" + "<img src=\"static/images/avatar.jpg\">"
                        + "</div>" + "<div>" + "<div class=\"right_triangle\"></div>"
                        + "<span>" + response.data.content + "</span>" + "</div>" + "</div>");
                    // 滑动滚动条到底部
                    //$(".chat").scrollTop = $(".chat").scrollHeight;
                    var chat = document.getElementsByClassName("chat")[0];
                    chat.scrollTop = chat.scrollHeight;
                    // 消息发送成功后清空输入框中的消息内容
                    $("#content").val("");
                },
                error: function() {

                }
            });
        });
    }, 1000);

    // 退出登录
    $("#loginOut").click(function() {
        //websocket.close
        if (!websocket.closed) {
            websocket.close();
        }
        location.href = "Login";
    });

    // 点击获取好友的聊天记录
    $(".message").click(function() {
        // 获取当前和自己聊天的用户id
        var to_user_id = $(this).attr("data-user-list-id");
        getChatRecord(to_user_id);
    });

    // 获取和指定好友的聊天记录
    function getChatRecord(to_user_id) {
        // 获取当前登录的用户的用户id
        var login_user_id = $(".chat").attr("data-user-id");
        console.log("to_user_id = " + to_user_id);
        // 设置将要聊天的用户的user_id
        $("#sendMessage").attr("data-to-user-id", to_user_id);
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
                // 滑动滚动条到底部
                //$(".chat").scrollTop = $(".chat").scrollHeight;
                var chat = document.getElementsByClassName("chat")[0];
                chat.scrollTop = chat.scrollHeight;
            },
            error:function () {

            }
        });
    }
});