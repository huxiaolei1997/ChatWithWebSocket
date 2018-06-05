package com.chat.controller;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xiaolei hu
 * @date 2018/6/4 16:36
 **/
@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    // 聊天主界面
    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String chat(HttpSession session, Model model) {
        // 获取当前用户的user_id
        Integer user_id = (Integer) session.getAttribute("user_id");
        // 获取用户好友列表
        List<User> userList = userService.getUserAllFriends(user_id);
        model.addAttribute("user_id", user_id);
        model.addAttribute("userList", userList);
        return "chat";
    }

    // 获取和某个用户的聊天记录
    @RequestMapping(value = "getMessageRecord", method = RequestMethod.GET)
    public @ResponseBody List<Message> getMessageRecord(HttpSession session, @RequestParam("to_user_id") Integer to_user_id) {
        Integer from_user_id = (Integer) session.getAttribute("user_id");
        Message message = new Message();
        message.setTo_user_id(to_user_id);
        message.setFrom_user_id(from_user_id);
        List<Message> messageList = userService.getMessageRecord(message);
        return messageList;
    }
}
