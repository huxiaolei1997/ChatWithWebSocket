package com.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xiaolei hu
 * @date 2018/6/4 16:36
 **/
@Controller
public class ChatController {

    // 聊天主界面
    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String chat() {
        return "chat";
    }
}
