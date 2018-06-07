$(function () {

    // 加载页面完成之后定位到用户名输入框
    $("#userName").focus();

    // 按回车键登录
    $(document).keydown(function(event) {
        if (event.keyCode == 13) {
            $("#userLogin").click();
        }
    });

    $("#userLogin").click(function() {
        // 获取用户名
        var userName = $("#userName").val();
        // 获取密码
        var password = $("#password").val();
        // 获取验证码
        var captcha = $("#captcha").val();

        // 这里对用户输入的数据进行初步校验
        if (userName == null || userName == "") {
            $("#userName").focus();
            $("#msgtips").text("用户名不能为空");
            return false;
        }
        if (password == null || password == "") {
            $("#password").focus();
            $("#msgtips").text("密码不能为空");
            return false;
        }
        if (captcha == null || captcha == "") {
            $("#captcha").focus();
            $("#msgtips").text("验证码不能为空");
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
                    // 检查用户密码
                    checkUserLogin(userName, password);
                } else {
                    $("#msgtips").text(response.msg);
                    $(".update-captcha").click();
                }
            },
            error: function() {

            }
        });

    });

    function checkUserLogin(userName, password) {
        // 用户登录校验
        $.ajax({
            url: "login",
            type: "POST",
            data: JSON.stringify({
                userName: userName,
                password: password
            }),
            contentType: "application/json",
            dataType: "json",
            success: function(response) {
                console.log(response);
                if (response.code == 0) {
                    location.href = 'chat';
                } else {
                    $("#msgtips").text(response.msg);
                    $(".update-captcha").click();
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