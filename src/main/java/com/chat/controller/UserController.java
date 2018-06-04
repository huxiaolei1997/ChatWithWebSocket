package com.chat.controller;

import com.chat.model.Result;
import com.chat.model.User;
import com.chat.service.UserService;
import com.chat.tools.Constant;
import com.chat.tools.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author xiaolei hu
 * @date 2018/6/3 19:57
 **/
@Controller
public class UserController {

    // 日志
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // 用户登录
//    @RequestMapping("/login/{user_id}")
//    public @ResponseBody String login(HttpSession session, @PathVariable("user_id") Integer user_id) {
//        System.out.println("LoginController : 当前登录的用户的id是：" + user_id);
//        session.setAttribute("user_id", user_id);
//        //System.out.println(session.getAttribute("user_id"));
//        return "success";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody Result<User> login(HttpSession session, @RequestBody User user) {
        logger.info("user:" + user.toString());
        User user2 = userService.getUserByUserNameAndPassword(user);
        System.out.println("LoginController : 当前请求登录的用户名是：" + user.getUserName());

        if (user2 != null) {
            logger.info("当前请求登录的用户名是：" + user2.getUserName());
            session.setAttribute("user_id", user2.getId());
            //result.setCode(0);
            // result.setData(user2);
            //result.setMsg("用户校验成功");
            return ResultUtil.success(user2);
        } else {
            return ResultUtil.error(1, "查询用户失败");
        }
    }

    // 登录页面
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String loingModel() {
        //List<UserTest> userTestList = userService.getUserAll();
        //logger.info("userTestList:" + userTestList.toString());
        //System.out.println(userTestList.toString());
        return "login";
    }

    // 生成验证码
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        // 获得 当前请求 对应的 会话对象
        HttpSession session = request.getSession();
        logger.info("开始生成验证码");
        final int width = 180; // 图片宽度
        final int height = 40; // 图片高度
        final String imgType = "jpeg"; // 指定图片格式 (不是指MIME类型)
        OutputStream output = null;
        try {
            output = response.getOutputStream();// 获得可以向客户端返回图片的输出流
            logger.info("验证码生成成功！"); // (字节流)
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建验证码图片并返回图片上的字符串
        String code = Constant.createCaptcha(width, height, imgType, output);
        // 建立 uri 和 相应的 验证码 的关联 ( 存储到当前会话对象的属性中 )
        session.setAttribute("captcha", code);
    }
}