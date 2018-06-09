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

    // websocket消息处理类
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    // 根据用户名查找用户
    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST)
    public @ResponseBody Pager<User> findUserByUserName(HttpSession session,@RequestParam("current_page") Integer current_page, @RequestParam("page_size") Integer page_size, @RequestParam("userName") String userName) {
        logger.info("根据用户名查找用户，" + userName);
        int user_id = (int) session.getAttribute("user_id");
        logger.info("current_page = " + current_page + ", page_size = " + page_size);
        // 默认分页大小
        //int page_size = Constant.PAGE_SIZE;
        Pager<User> userPager = userService.findUserByUserName(current_page, page_size, userName, user_id);
        logger.info("根据用户名查找用户：" + userPager.toString());
        //return userList;
        return userPager;
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

    // 处理好友请求
    @RequestMapping(value = "processUserRequest", method = RequestMethod.POST)
    public @ResponseBody Result<Friend> processUserRequest(@RequestBody Friend friend) {
        userService.processUserRequest(friend);
        User user = userService.getUserInfo(friend.getA_id());
        if (friend.getStatus() == Constant.ACCESS) {
            return ResultUtil.success(friend, "您接受了<b>" + user.getUserName() + "</b>的申请");
        } else if (friend.getStatus() == Constant.DENY) {
            return ResultUtil.success(friend, "您拒绝了<b>" + user.getUserName() + "</b>的申请");
        }
        return null;
    }

    // 获取验证消息处理结果
    @RequestMapping(value = "getVerificationResult", method = RequestMethod.GET)
    public @ResponseBody List<MessageProcessResult<User>> getVerificationResult(HttpSession session) {
        List<MessageProcessResult<User>> messageProcessResultList = userService.getUserRequestByUserId(session);
        return messageProcessResultList;
    }

    // 获取当前用户的好友列表
    @RequestMapping(value = "getUserFriendList", method = RequestMethod.GET)
    public @ResponseBody List<User> getUserFriend(HttpSession session) {
        // 获取当前登录的用户的id
        logger.info("获取当前登录的用户的id");
        int user_id = (int) session.getAttribute("user_id");
        logger.info("获取当前登录的用户的好友列表");
        List<User> userList = userService.getUserAllFriends(user_id);
        return userList;
    }
}