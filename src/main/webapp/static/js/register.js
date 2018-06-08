$(function() {
    // 加载页面完成之后定位到用户名输入框
    //$("#userName").focus();

   // 按回车键登录
    $(document).keydown(function(event) {
        if (event.keyCode == 13) {
            $("#register").click();
        }
    });

    // 检查用户名是否存在
    $("#userName").blur(function() {
        // 用户名
        var userName = $(this).val().trim();
        if (userName == null || userName == "") {
            $("#msgtips").text("用户名不能为空");
            $("#userName").focus();
            return false;
        }

        $.ajax({
            url: "checkUserNameIfExist",
            type: "POST",
            data: {
                userName: userName
            },
            dataType: "json",
            success: function(response) {
                if (response.code == 1) {
                    $("#msgtips").text(response.msg);
                    $("#userName").focus();
                } else {
                    $("#msgtips").text(response.msg);
                }
            },
            error: function () {

            }
        });
    });

    // 注册用户
    $("#register").click(function() {
        // 获取用户名
        var userName = $("#userName").val().trim();
        // 获取密码
        var password = $("#password").val().trim();
        // 获取重复输入的密码
        var repeatPassword = $("#repeatpassword").val().trim();
        // 获取验证码
        var captcha = $("#captcha").val().trim();

        // 校验用户名
        if (userName == null || userName == "") {
            $("#msgtips").text("用户名不能为空");
            $("#userName").focus();
            return false;
        }

        // 校验密码
        if (password == null || password == "") {
            $("#msgtips").text("密码不能为空");
            $("#password").focus();
            return false;
        }

        if (repeatPassword == null || repeatPassword == "") {
            $("#msgtips").text("请确认输入的密码");
            $("#repeatpassword").focus();
            return false;
        }

        // 判断两次密码是否输入一致
        if (password != repeatPassword) {
            $("#msgtips").text("两次密码输入不一致");
            $("#repeatpassword").focus();
            return false;
        }

        // 对验证码进行校验
        if (captcha == null || captcha == "") {
            $("#msgtips").text("验证码不能为空");
            $("#captcha").focus();
            return false;
        }

        // 校验验证码是否正确
        $.ajax({
            url: "checkCaptcha",
            type: "POST",
            data: {
                captcha_client : captcha
            },
            dataType: "json",
            success: function(response) {
                if (response.code == 0) {
                    registerUser(userName, password);
                } else {
                    $("#msgtips").text(response.msg);
                    $(".update-captcha").click();
                }
            },
            error: function() {

            }
        });
    });

    // 用户注册
    function registerUser(userName, password) {
        $.ajax({
            url: "register",
            type: "POST",
            data: JSON.stringify({
               userName: userName,
               password: password
            }),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                if (response.code == 0) {
                    $("#msgtips").text(response.msg);
                    setTimeout(function() {
                        location.href = "Login";
                    }, 1500);
                } else {
                    $("#msgtips").text(response.msg);
                }
            },
            error: function() {

            }
        });
    }

    // 切换验证码
    $(".update-captcha").click(function() {
        var date = new Date();
        var captchaUrl = "captcha?timestamp=" + date.valueOf();
        $(".update-captcha").attr("src", captchaUrl);
    });
});
