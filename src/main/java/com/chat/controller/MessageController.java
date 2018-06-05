package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.Result;
import com.chat.service.MessageService;
import com.chat.service.MyWebSocketHandler;
import com.chat.tools.ResultUtil;
import com.sun.tracing.dtrace.Attributes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private MessageService messageService;

    // 日志
    private static final Logger logger = Logger.getLogger(MessageController.class);

    // 给好友发送消息
    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public @ResponseBody Result<Message> sendMessage(@RequestBody Message message) {
        try {
            // 发送消息给指定的好友
            myWebSocketHandler.sendMessageToUser(message.getTo_user_id(), new TextMessage(message.getContent()));
            // 保存消息内容到数据库中
            logger.info("保存消息内容到数据库中，消息内容是" + message.toString());
            messageService.saveChatRecord(message);
            return ResultUtil.success(message);
        } catch (IOException e) {
            logger.info("LoginController, sendMessage() " + e.getMessage());
            e.printStackTrace();
            return ResultUtil.error(1, e.getMessage());
        }
    }
}
