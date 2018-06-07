package com.chat.service;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.model.UserTest;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xiaolei hu
 * @date 2018/6/4 19:11
 **/
public interface UserService {

    /**
     * 获取用户个人信息
     * @param user_id
     * @return
     */
    User getUserInfo(int user_id);

    /**
     * 查询用户好友
     * @param user_id
     * @return
     */
    List<User> getUserAllFriends(int user_id);

    /**
     * 检查用户名和密码是否正确
     * @param user
     * @return
     */
    User getUserByUserNameAndPassword(User user);

    /**
     * 检查用户名是否存在
     * @param userName
     * @return
     */
    long checkUserNameIfExist(String userName);

    /**
     * 获取和指定用户的聊天记录
     * @param message
     * @return
     */
    List<Message> getMessageRecord(Message message);
}
