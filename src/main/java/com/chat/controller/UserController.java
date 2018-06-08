package com.chat.controller;

import com.chat.model.*;
import com.chat.service.ToolsService;
import com.chat.service.UserService;
import com.chat.serviceImpl.MyWebSocketHandler;
import com.chat.tools.Constant;
import com.chat.tools.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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

    // websocket消息处理类
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

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

    // 保存注册的用户到数据库中
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody Result saveRegisterUser(@RequestBody User user) {
        logger.info("调用注册用户的方法，" + user.toString());
        userService.saveRegisterUser(user);
        logger.info("用户信息成功保存到数据库中");
        return ResultUtil.success(0, "用户注册成功");
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

    // 根据用户名查找用户
    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST)
    public @ResponseBody List<User> findUserByUserName(HttpSession session, @RequestParam("userName") String userName) {
        logger.info("根据用户名查找用户，" + userName);
        int user_id = (int) session.getAttribute("user_id");
        List<User> userList = userService.findUserByUserName(userName, user_id);
        logger.info("根据用户名查找用户：" + userList.toString());
        return userList;
    }

    // 发送好友请求
    @RequestMapping(value = "sendFriendRequest", method = RequestMethod.POST)
    public @ResponseBody Result sendFriendRequest(@RequestBody Message message) {
        logger.info("发送好友请求：" + message.toString());
        // 获取发送好友请求的用户的用户名
        User user = userService.getUserInfo(message.getFrom_user_id());
        message.setContent(user.getUserName());
        message.setMessage_type(Constant.VERIFACTION_MESSAGE);
        try {
            // 首先检查用户是否在线，如果用户在线则直接发送消息给用户，再保存到数据库中
            if (myWebSocketHandler.checkUserIfOnline(message.getTo_user_id())) {
                logger.info("用户在线，直接发送消息给用户");
                myWebSocketHandler.sendMessageToUser(message.getTo_user_id(), new TextMessage(message.toString()));
                userService.saveFriendRequest(message);
            } else {
                logger.info("用户不在线，先保存好友请求到数据库中");
                userService.saveFriendRequest(message);
            }
            return ResultUtil.success(0, "发送好友请求成功");
        } catch (IOException e) {
            logger.info("LoginController, sendMessage() " + e.getMessage());
            e.printStackTrace();
            return ResultUtil.error(1, e.getMessage());
        }

    }

    // 生成验证码
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        toolsService.makeCaptcha(request, response);
    }

    // 处理好友请求
    @RequestMapping(value = "processUserRequest", method = RequestMethod.POST)
    public @ResponseBody Result<Friend> processUserRequest(@RequestBody Friend friend) {
        userService.processUserRequest(friend);
        return ResultUtil.success(0, "处理成功");
    }

    // 获取验证消息处理结果
    @RequestMapping(value = "getVerificationResult", method = RequestMethod.GET)
    public @ResponseBody List<MessageProcessResult<User>> getVerificationResult(HttpSession session) {
        // 获取当前登录的用户id
        int user_id = (int) session.getAttribute("user_id");
        // 获取和当前用户有关的验证消息
        logger.info("获取和" + user_id + "有关的好友请求");
        List<Friend> friendList = userService.getUserRequestByUserId(user_id);
        logger.info("和" + user_id + "有关的好友请求是：" + friendList.toString());
        String processResult = "";
        List<MessageProcessResult<User>> messageProcessResultList = new ArrayList<>();
        MessageProcessResult<User> messageProcessResult = new MessageProcessResult<>();
        User fromUser;
        User toUser;
        int index = 0;
        for (Friend friend : friendList) {
            logger.info("这是第" + index + "循环");
             fromUser = userService.getUserInfo(friend.getA_id());
             toUser = userService.getUserInfo(friend.getB_id());
             //logger.info("fromUs");
            logger.info("fromUser:" + fromUser.toString() + ", getA_id() = " + friend.getA_id() + ", toUser" + toUser.toString() + ", getB_id() = " + friend.getB_id());
            // 自己添加好友
            if (friend.getA_id() == user_id) {
                logger.info("自己添加好友，添加的好友id是 " + friend.getB_id());
                if (friend.getStatus() == Constant.ACCESS) {
                    processResult = toUser.getUserName() + "接受了您的好友请求";
                } else {
                    processResult = toUser.getUserName() + "拒绝了您的好友请求";
                }
                logger.info("processResult: " + processResult);
            } else if (friend.getA_id() != user_id) {
                logger.info("别人添加自己为好友，别人的id是 " + friend.getA_id());
                //
                if (friend.getStatus() == Constant.ACCESS) {
                    processResult = "您接受了" + fromUser.getUserName() + "的好友请求";
                } else if (friend.getStatus() == Constant.DENY) {
                    processResult = "您拒绝了" + fromUser.getUserName() + "的好友请求";
                }
                logger.info("processResult: " + processResult);
            }
            messageProcessResult.setData1(fromUser);
            messageProcessResult.setData2(toUser);
            messageProcessResult.setProcess_result(processResult);
            messageProcessResult.setStatus(friend.getStatus());
            logger.info("messageProcessResult.getData1(): " + messageProcessResult.getData1().toString() + ", messageProcessResult.getData2()" + messageProcessResult.getData2().toString());
            logger.info("messageProcessResult.getProcess_result():" + messageProcessResult.getProcess_result());
            messageProcessResultList.add(messageProcessResult);
            logger.info("messageProcessResult" + messageProcessResult.toString() + ", " + messageProcessResultList.toString());
            index++;
        }
        logger.info("验证消息的处理结果是：" + messageProcessResultList.toString());
        return messageProcessResultList;
    }
}