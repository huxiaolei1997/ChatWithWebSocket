package com.chat.controller;

import com.chat.model.Result;
import com.chat.model.User;
import com.chat.service.ToolsService;
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

    @Autowired
    private ToolsService toolsService;

    // 检查验证码是否正确
    @RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST)
    public @ResponseBody Result checkCaptcha(HttpSession session, @RequestParam("captcha_client") String captcha_client) {
        String captcha_server = (String) session.getAttribute("captcha");
        logger.info("开始检查验证码是否正确，captcha_client = " + captcha_client + ", captcha_server = " + captcha_server);
        if (captcha_client != null && captcha_client.equalsIgnoreCase(captcha_server)) {
            logger.info("验证码校验正确");
            return ResultUtil.success(0, "验证码校验正确");
        } else {
            return ResultUtil.error(1, "验证码校验失败");
        }
    }

    // 检查用户的用户名密码是否正确
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody Result<User> login(HttpSession session, @RequestBody User user) {
        logger.info("user:" + user.toString());
        User user2 = userService.getUserByUserNameAndPassword(user);
        System.out.println("LoginController : 当前请求登录的用户名是：" + user.getUserName());
        if (user2 != null) {
            logger.info("当前请求登录的用户名是：" + user2.getUserName());
            session.setAttribute("user_id", user2.getId());
            return ResultUtil.success(user2);
        } else {
            return ResultUtil.error(1, "用户名不存在或密码错误");
        }
    }

    // 登录页面
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String loingModel() {
        return "login";
    }

    // 用户注册界面
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    // 检查用户名是否存在
    @RequestMapping(value = "/checkUserNameIfExist", method = RequestMethod.POST)
    public @ResponseBody Result checkUserNameIfExist(@RequestParam("userName") String userName) {
        logger.info("检查 " + userName + " 是否存在");
        long if_user_name_exist = userService.checkUserNameIfExist(userName);
        if (if_user_name_exist == 1) {
            //Result result = new Result();
            logger.info(userName + " 已经被占用");
            return ResultUtil.error(1, "该用户名已经被占用");
        } else if (if_user_name_exist == 0) {
            logger.info(userName + " 可用");
            return ResultUtil.error(0, "该用户名可用");
        }
        return null;
    }

    // 生成验证码
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        toolsService.makeCaptcha(request, response);
    }
}