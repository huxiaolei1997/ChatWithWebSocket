package com.chat.serviceImpl;

import com.chat.mapper.UserMapper;
import com.chat.model.Message;
import com.chat.model.User;
import com.chat.service.MyWebSocketHandler;
import com.chat.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xiaolei hu
 * @date 2018/6/4 19:10
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    // 日志
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;


    /**
     * 获取用户个人信息
     * @param user_id
     * @return
     */
    @Override
    public User getUserInfo(int user_id) {
        User user = userMapper.getUserInfo(user_id);
        return user;
    }

    /**
     * 查询用户所有好友
     * @param user_id
     * @return
     */
    @Override
    public List<User> getUserAllFriends(int user_id) {
        List<User> userList = userMapper.getUserAllFriends(user_id);
        return userList;
    }

    /**
     * 检查用户名和密码是否正确
     * @param user
     * @return
     */
    @Override
    public User getUserByUserNameAndPassword(User user) {
        User user2 = userMapper.getUserByUserNameAndPassword(user);
        return user2;
    }

    /**
     * 获取和指定用户的聊天记录
     *
     * @param message
     * @return
     */
    @Override
    public List<Message> getMessageRecord(Message message) {
        List<Message> messageList = userMapper.getMessageRecord(message);
        return messageList;
    }
}