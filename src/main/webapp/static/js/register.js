$(function() {
    // 加载页面完成之后定位到用户名输入框
    $("#userName").focus();

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
});
