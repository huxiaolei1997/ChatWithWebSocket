package com.chat.service;

import com.chat.model.*;

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

    /**
     * 保存注册的用户到数据库中
     * @param user
     */
    void saveRegisterUser(User user);

    /**
     * 根据用户名查找用户（不显示自己）
     * @param userName
     * @return
     */
    List<User> findUserByUserName(String userName, int user_id);

    /**
     * 保存好友请求到数据库中
     * @param message
     */
    void saveFriendRequest(Message message);

    /**
     * 处理好友请求
     * @param friend
     */
    void processUserRequest(Friend friend);

    /**
     * 获取和当前用户有关的好友验证请求及处理结果
     * @param session
     * @return
     */
    List<MessageProcessResult<User>> getUserRequestByUserId(HttpSession session);
}
