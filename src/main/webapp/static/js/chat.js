$(function() {
    // 获取好友的数量
    var data_user_list_length = $(".user-list").attr("data-user-list-length");
    console.log(data_user_list_length);
    if (data_user_list_length > 0) {
        // 设置默认发送信息给第一个好友
        var to_user_id_default = $(".message").eq(0).attr("data-user-list-id");
        $(".message").eq(0).addClass("user-select");
        console.log("默认发送消息给第一个好友：" + to_user_id_default);
        $("#sendMessage").attr("data-to-user-id", to_user_id_default);
        // 默认获取和第一个好友的聊天记录
        getChatRecord(to_user_id_default);
    }

    var websocket;
    setTimeout(function() {
        // 首先判断是否 支持 WebSocket
        if('WebSocket' in window) {
            websocket = new WebSocket("ws://10.62.174.143:8088/ChatWithWebSocket/websocket");
        } else if('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://10.62.174.143:8088/ChatWithWebSocket/websocket");
        } else {
            websocket = new SockJS("http://10.62.174.143:8088/ChatWithWebSocket/sockjs/websocket");
        }

        // 打开时
        websocket.onopen = function(evnt) {
            console.log("  websocket.onopen  ");
        };

        // 处理消息时
        websocket.onmessage = function(evnt) {
            console.log("evet: " + evnt);
            console.log("收到了一条消息，消息内容是：" + evnt.data);
            var data = JSON.parse(evnt.data);
            // 如果收到的是普通消息
            if (data.message_type == 0) {
                //$("#msg").append("<p>(<font color='red'>" + evnt.data + "</font>)</p>");
                $(".chat").append("<div class=\"sender\">" + "<div>"
                    + "<img src=\"static/images/avatar.jpg\">"
                    + "</div>" + "<div>" + "<div class=\"left_triangle\"></div>"
                    + "<span>" + data.content + "</span>" + "</div>" + "</div>");
                // 滑动滚动条到底部
                var chat = document.getElementsByClassName("chat")[0];
                chat.scrollTop = chat.scrollHeight;
            } else if (data.message_type == 1) {
                // 如果收到的是验证消息
                $("#system-message div ul").prepend("<li data-from-user-id=\"" +
                    data.from_user_id + "\"><span>" + data.content +
                    "请求添加您为好友</span><br><button class=\"user-request btn btn-primary\" user-status=\"0\">接受</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<button class=\"user-request btn btn-primary\" user-status=\"1\">拒绝</button></li>");
            }

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
            if(message_content == null || message_content.trim() == ""){
                //alert("message_content can not empty!!");
                var msg = "消息内容不能为空";
                msgtips(msg);
                $("#content").val("");
                return false;
            }
            // 获取收信人的id
            var to_user_id = $(this).attr("data-to-user-id");
            if (to_user_id == null || to_user_id == "") {
                //alert("获取不到收信人的id");
                var msg = "获取不到收信人的id";
                msgtips(msg);
                return false;
            }
            console.log("收信人id:" + to_user_id);
            // 获取发信人的id
            var from_user_id = $(".chat").attr("data-user-id");
            console.log("发信人id:" + from_user_id);
            if (from_user_id == null || from_user_id == "") {
                //alert("获取不到发信人的id");
                var msg = "获取不到发信人的id";
                msgtips(msg);
                return false;
            }
            // 发送时间
            var send_time = new Date();
            $.ajax({
                url: "message",
                type: "POST",
                data: JSON.stringify({
                    from_user_id : from_user_id,
                    to_user_id : to_user_id,
                    content: message_content,
                    send_time: send_time,
                    message_type: 0
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
       $(".user").each(function() {
           if ($(this).hasClass("user-select")) {
               $(this).removeClass("user-select");
           }
       });
       $(this).addClass("user-select");
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

    // 添加好友面板
    $(".add-friend").click(function() {
        $(this).addClass("span-select");
        $(".system-message").removeClass("span-select");
        $("#system-message").css('display', 'none');
        $("#add-user").css('display', 'block');
        $("#find-result-list ul").html("");
    });

    // 查找用户
    $("#find-user").click(function() {
        // 获取用户名
        var userName = $("#userName").val().trim();

        if (userName == null || userName == "") {
            //alert("请输入要查找的用户名");
            var msg = "请输入要查找的用户名";
            msgtips(msg);
            return false;
        }

        $.ajax({
            url: "findUserByUserName",
            type: "POST",
            data: {
                userName: userName
            },
            dataType: "json",
            success: function (response) {
                console.log(response);
                $("#find-result-list ul").html("");
                $("#find-result-list ul").append("<li><span>搜索结果</span></li>");
                var dataFindResult = "";
                for (var i = 0; i < response.length; i++) {
                    dataFindResult += "<li><span>" + response[i].userName + "</span><button class=\"add-friend-request btn btn-primary\" " +
                        "data-add-user-id=\"" + response[i].id + "\">加为好友</button></li>";
                }
                $("#find-result-list ul").append(dataFindResult);
                // 给动态添加的节点绑定事件
                $("body").on("click", ".add-friend-request" , function() {
                    // 获取当前点击button相对于同类元素的位置
                    var index = $(this).parent().index();
                    console.log("当前点击的是第" + index + "个li");
                    $(this).attr("disabled", "disabled");
                    // 获取当前登录的用户的用户id
                    var from_user_id = $(".chat").attr("data-user-id");
                    // 获取要添加为好友的用户的id
                    var to_user_id = $(this).attr("data-add-user-id");
                    //alert(a_id + ", " + b_id);
                    //return false;
                    if (from_user_id == null || from_user_id == "") {
                        //alert("获取不到a_id的值");
                        var msg = "获取不到a_id的值";
                        msgtips(msg);
                        return false;
                    }

                    if (to_user_id == null || to_user_id == "") {
                        //alert("获取不到b_id的值");
                        var msg = "获取不到b_id的值";
                        msgtips(msg);
                        return false;
                    }
                    // $.ajax({
                    //     url: "",
                    //     type: "POST",
                    //     data: {
                    //
                    //     },
                    //     dataType: "json",
                    //     success: function (response) {
                    //
                    //     },
                    //     error: function () {
                    //
                    //     }
                    // });
                    //发送好友请求
                    $.ajax({
                        url: "sendFriendRequest",
                        type: "POST",
                        data: JSON.stringify({
                            from_user_id: from_user_id,
                            to_user_id: to_user_id,
                            content: "test",
                            message_type: 1,
                            send_time: new Date()
                        }),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (response) {
                            $("#find-result-list ul li").eq(index).find("button").removeAttr("disabled");
                            //alert(response.msg);
                            //var msg = "获取不到b_id的值";
                            msgtips(response.msg);
                        },
                        error: function() {

                        }
                    });
                });
            },
            error: function() {

            }
        });

    });

    // 验证消息面板
    $(".system-message").click(function() {
        $(this).addClass("span-select");
        $(".add-friend").removeClass("span-select");
        $("#system-message").css('display', 'block');
        $("#add-user").css('display', 'none');
        // 获取当前登录的用户的用户id
        var user_id = $(".chat").attr("data-user-id");

        $.ajax({
            url: "getVerificationResult",
            type: "GET",
            dataType: "json",
            success: function (response) {
                console.log("验证消息处理结果是：" + response);
                var dataVerification = "";
                for (key in response) {
                    if (response[key].status == 2) {
                        if (response[key].data1.id != user_id) {
                            dataVerification += "<li data-from-user-id=\"" +
                            response[key].data1.id + "\"><span>" + response[key].process_result +
                            "</span><br><button class=\"user-request btn btn-primary\" user-status=\"0\">接受</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                            "<button class=\"user-request btn btn-primary\" user-status=\"1\">拒绝</button></li>";
                        } else {
                            dataVerification += "<li data-from-user-id=\"" +
                                response[key].data1.id + "\"><span>" + response[key].process_result +
                                "</span><br>" + "</li>";
                        }
                    } else {
                        dataVerification += "<li data-from-user-id=\"" +
                            response[key].data1.id + "\"><span>" + response[key].process_result +
                            "</span><br>" + "</li>";
                    }
                    //
                }
                console.log(dataVerification);
                $("#system-message div ul").html(dataVerification);
            },
            error: function () {
                
            }
        });
    });

    // 处理好友请求
    $("body").on("click", ".user-request", function () {
        // 获取当前登录的用户的用户id
        var b_id = $(".chat").attr("data-user-id");
        // 获取发起好友请求的用户的id
        var a_id = $(this).parent().attr("data-from-user-id");
        // 获取status
        var status = $(this).attr("user-status");

        if (b_id == null || b_id == "") {
            //alert("b_id的值获取不到");
            var msg = "b_id的值获取不到";
            msgtips(msg);
            return false;
        }

        if (a_id == null || a_id == "") {
            //alert("a_id的值获取不到");
            var msg = "a_id的值获取不到";
            msgtips(msg);
            return false;
        }

        if (status == null || status == "") {
            //alert("status的值获取不到");
            var msg = "status的值获取不到";
            msgtips(msg);
            return false;
        }

        $.ajax({
            url: "processUserRequest",
            type: "POST",
            data: JSON.stringify({
                a_id: a_id,
                b_id: b_id,
                status: status,
                add_time: new Date()
            }),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                $(".system-message").click();
                msgtips(response.msg);
                //alert(response.msg);
            },
            error: function () {

            }
        });
    });

    var msgtips = function(msg) {
        $(".alert-success").css("display", "block");
        $(".alert-success").html(msg);
        setTimeout(function () {
            $(".alert-success").css("display", "none");
        }, 1500);
    }
});

