package com.chat.controller;

import com.chat.service.MyWebSocketHandler;
import com.sun.tracing.dtrace.Attributes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xiaolei hu
 * @date 2018/6/3 19:57
 **/
@Controller
public class LoginController {
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;
    // 日志
    private static final Logger logger = Logger.getLogger(LoginController.class);

    // 用户登录
    @RequestMapping("/login/{user_id}")
    public @ResponseBody String login(HttpSession session, @PathVariable("user_id") Integer user_id) {
        System.out.println("LoginController : 当前登录的用户的id是：" + user_id);
        session.setAttribute("user_id", user_id);
        //System.out.println(session.getAttribute("user_id"));
        return "success";
    }

    @RequestMapping("/message/{user_id}/{message}")
    public @ResponseBody String sendMessage(@PathVariable("user_id") Integer user_id, @PathVariable("message") String message) {
        String if_send_success;
        try {
             if_send_success = "success";
             myWebSocketHandler.sendMessageToUser(user_id, new TextMessage(message));
             return if_send_success;
        } catch (IOException e) {
            if_send_success = "error";
            System.out.println("LoginController, sendMessage() " + e.getMessage());
            logger.info("LoginController, sendMessage() " + e.getMessage());
            e.printStackTrace();
        }
        return if_send_success;
    }
}
