$(function() {

    var findUserByUserName = function(userName, current_page) {
        $.ajax({
            url: "findUserByUserName",
            type: "POST",
            data: {
                userName: userName,
                current_page: current_page,
                page_size: 10
            },
            dataType: "json",
            success: function (response) {
                console.log(response);
                $("#find-result-list ul").html("");
                $("#find-result-list ul").append("<li><span>搜索结果（总共 " + response.total_record + " 条记录）</span></li>");

                if (response.total_page > 1) {
                    $(".add-friend-pager").css("display", "block");
                } else {
                    $(".add-friend-pager").css("display", "none");
                }

                if (response.total_record == 0) {
                    $(".add-friend-pager").css("display", "none");
                    var msg = "查询结果为空";
                    msgtips(msg);
                }

                var dataList = response.dataList;
                var length = dataList.length;
                var dataFindResult = "";
                for (var i = 0; i < length; i++) {
                    dataFindResult += "<li><span>" + dataList[i].userName + "</span><button class=\"add-friend-request btn btn-primary\" " +
                        "data-add-user-id=\"" + dataList[i].id + "\">加为好友</button></li>";
                }

                $(".add-friend-pager").attr("data-current-page", response.current_page);

                $(".add-friend-pager").attr("data-total-page", response.total_page);
                $(".add-friend-pager").eq(1).find("span").eq(1).text(response.total_page);
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

                    // 发送好友请求
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
    }

    // 获取和指定好友的聊天记录
    var getChatRecord = function(to_user_id) {
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

    // 消息提示
    var msgtips = function(msg) {
        $(".alert-success").css("display", "block");
        $(".alert-success").html(msg);
        setTimeout(function () {
            $(".alert-success").css("display", "none");
        }, 1500);
    }

    // 获取用户好友列表
    var getUserFriendList = function () {
        $.ajax({
            url: "getUserFriendList",
            type: "GET",
            dataType: "json",
            success: function (response) {
                var dataUserListHtml = "";
                var dataLength = response.length;
                for (var i = 0; i < dataLength; i++) {
                    dataUserListHtml += "<span class=\"user message\" data-user-list-id=\"" + response[i].id + "\">" +
                        "<img src=\"static/images/avatar.jpg\" alt=\"avatar\" class=\"avatar\">" +
                        "<span>" + response[i].userName + "</span></span>";
                }
                console.log("dataUserListHtml" + dataUserListHtml);
                $(".user-list").html(dataUserListHtml);
                // 设置默认发送信息给第一个好友
                var to_user_id_default = $(".message").eq(0).attr("data-user-list-id");
                $(".message").eq(0).addClass("user-select");
                console.log("默认发送消息给第一个好友：" + to_user_id_default);
                $("#sendMessage").attr("data-to-user-id", to_user_id_default);
                // 默认获取和第一个好友的聊天记录
                getChatRecord(to_user_id_default);
            },
            error: function() {

            }
        });
    }

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

    // 延迟 1000ms 建立 websocket 连接
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
                //if (data.from_user_id)
                $(".message").each(function () {
                    var from_user_id = $(this).attr("data-user-list-id");
                    if ($(this).hasClass("user-select")) {
                        console.log("移除user-select");
                        $(this).removeClass("user-select");
                    }
                    if (from_user_id == data.from_user_id) {
                        console.log("index = " + $(this).index());
                        if ($(this).index() != 0) {
                            // 消息聊天置顶
                            var dataHtml = $(this).prop("outerHTML");
                            $(".user-list").prepend(dataHtml);
                            $(this).remove();
                        }
                        // 获取和当前发来消息的用户的历史聊天记录
                        getChatRecord(from_user_id);
                        // 这里的 return false 的作用相当于 break，即退出本次循环
                        //return false;
                    }
                });
                // 发消息的用户当前为选中状态
                $(".message").eq(0).addClass("user-select");
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

        // 连接出现错误时
        websocket.onerror = function(evnt) {
            console.log("  websocket.onerror  ");
        };

        // 连接关闭时
        websocket.onclose = function(evnt) {
            console.log("  websocket.onclose  ");
        };
    }, 1000);

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

    // 退出登录
    $("#loginOut").click(function() {
        //websocket.close
        // 关闭websocket连接
        if (!websocket.closed) {
            websocket.close();
        }
        $.ajax({
            url: "loginOut",
            type: "GET",
            dataType: "json",
            success:function(response) {
                if (response.code == 0) {
                    location.href = "Login";
                } else {
                    var msg = "退出登录失败";
                    msgtips(msg);
                    //return false;
                }
            },
            error: function() {

            }
        });

    });

    // 点击获取好友的聊天记录
    $(".user-list").on("click", ".message", function () {
        $(".user").each(function() {
            if ($(this).hasClass("user-select")) {
                $(this).removeClass("user-select");
            }
        });
        $(this).addClass("user-select");
        // 获取当前和自己聊天的用户id
        var to_user_id = $(this).attr("data-user-list-id");
        // 获取聊天记录
        getChatRecord(to_user_id);
    });

    // 添加好友面板
    $(".add-friend").click(function() {
        $(this).addClass("span-select");
        $(".system-message").removeClass("span-select");
        $("#system-message").css('display', 'none');
        $("#add-user").css('display', 'block');
        $(".add-friend-pager").css("display", "none");
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
        findUserByUserName(userName, 1);
    });

    // 验证消息面板
    $(".system-message").click(function() {
        $(this).addClass("span-select");
        $(".add-friend").removeClass("span-select");
        $("#system-message").css('display', 'block');
        $("#add-user").css('display', 'none');
        $(".add-friend-pager").css("display", "none");
        // 获取当前登录的用户的用户id
        var user_id = $(".chat").attr("data-user-id");

        $.ajax({
            url: "getVerificationResult",
            type: "GET",
            dataType: "json",
            success: function (response) {
                console.log("验证消息处理结果是：" + response);
                var dataVerification = "";
                //var response_format = FastJson.format(response);
                var response_format = response;
                for (key in response_format) {
                    console.log("response_format = " + response_format[0].data1);
                    if (response_format[key].status == 2) {
                        // 别人添加自己为好友
                        if (response_format[key].data1.id != user_id) {
                            dataVerification += "<li data-from-user-id=\"" +
                                response_format[key].data1.id + "\"><span>" + response_format[key].process_result
                                + "</span><br><button class=\"user-request btn btn-primary\" user-status=\"0\">接受</button>" +
                                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "<button class=\"user-request btn btn-primary\" user-status=\"1\">拒绝</button></li>";
                        } else if (response_format[key].data1.id == user_id) {
                            // 添加别人为好友
                            dataVerification += "<li data-from-user-id=\"" +
                                response_format[key].data1.id + "\"><span>" + response_format[key].process_result +
                                "</span><br>" + "</li>";
                        }
                    } else {
                        dataVerification += "<li data-from-user-id=\"" +
                            response_format[key].data1.id + "\"><span>" + response_format[key].process_result +
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
                if (response.data.status == 0) {
                    getUserFriendList();
                }
                //alert(response.msg);
            },
            error: function () {

            }
        });
    });

    $(".add-friend-pager li a").click(function() {
        // 获取当前页号
        var current_page = parseInt($(this).parent().parent().attr("data-current-page"));

        // 获取要查找的用户名
        var userName = $("#userName").val().trim();

        // 上一页
        if ($(this).hasClass("prev")) {
            current_page -= 1;
            if (current_page <= 1) {
                current_page = 1;
                $(".add-friend-pager li .prev").css("display", "none");
            }
            $(".add-friend-pager").eq(1).find("span").eq(0).text(current_page);
            $(".add-friend-pager li .next").css("display", "inline-block");
            findUserByUserName(userName, current_page);
            console.log("current_page = " + current_page + ", userName = " + userName);
        } else if ($(this).hasClass("next")) {
            // 下一页
            var total_page = $(this).parent().parent().attr("data-total-page");
            current_page += 1;
            if (current_page >= total_page) {
                current_page = total_page;
                $(".add-friend-pager li .next").css("display", "none");
            }
            $(".add-friend-pager").eq(1).find("span").eq(0).text(current_page);
            $(".add-friend-pager li .prev").css("display", "inline-block");
            findUserByUserName(userName, current_page);
            console.log("current_page = " + current_page + ", userName = " + userName);
        }
    });
});