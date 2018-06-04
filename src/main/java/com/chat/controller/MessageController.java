package com.chat.controller;

import com.chat.service.MyWebSocketHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

/**
 * 消息控制器
 * @author xiaolei hu
 * @date 2018/6/4 16:31
 **/
@Controller
public class MessageController {

    // websocket消息处理类
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    // 日志
    private static final Logger logger = Logger.getLogger(MessageController.class);

    @RequestMapping("/message/{user_id}/{message}")
    public @ResponseBody
    String sendMessage(@PathVariable("user_id") Integer user_id, @PathVariable("message") String message) {
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
