package com.chat.serviceImpl;

import com.chat.mapper.UserMapper;
import com.chat.model.Friend;
import com.chat.model.Message;
import com.chat.model.MessageProcessResult;
import com.chat.model.User;
import com.chat.service.UserService;
import com.chat.tools.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        List<User> userList = userMapper.getUserAllFriends(user_id, Constant.ACCESS);
        return userList;
    }

    /**
     * 检查用户名和密码是否正确
     * @param user
     * @return
     */
    @Override
    public User getUserByUserNameAndPassword(User user) {
        return userMapper.getUserByUserNameAndPassword(user);
    }

    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    @Override
    public long checkUserNameIfExist(String userName) {
        return userMapper.checkUserIfExist(userName);
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

    /**
     * 保存注册的用户到数据库中
     *
     * @param user
     */
    @Override
    public void saveRegisterUser(User user) {
        userMapper.saveRegisterUser(user);
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    @Override
    public List<User> findUserByUserName(String userName, int user_id) {
        return userMapper.findUserByUserName(userName, user_id);
    }

    /**
     * 保存好友请求到数据库中
     *
     * @param message
     */
    @Override
    public void saveFriendRequest(Message message) {
        userMapper.saveFriendRequest(message);
    }

    /**
     * 处理好友请求
     *
     * @param friend
     */
    @Override
    public void processUserRequest(Friend friend) {
        userMapper.processUserRequest(friend);
    }

    /**
     * 获取和当前用户有关的好友验证请求及处理结果
     *
     * @param session
     * @return
     */
    @Override
    public List<MessageProcessResult<User>> getUserRequestByUserId(HttpSession session) {
        // 获取当前登录的用户id
        int user_id = (int) session.getAttribute("user_id");
        // 获取和当前用户有关的验证消息
        logger.info("获取和" + user_id + "有关的好友请求");
        List<Friend> friendList = userMapper.getUserRequestByUserId(user_id);
        logger.info("和" + user_id + "有关的好友请求是：" + friendList.toString());
        String processResult = "";
        List<MessageProcessResult<User>> messageProcessResultList = new ArrayList<>();
        MessageProcessResult<User> messageProcessResult;
        User fromUser;
        User toUser;
        //int index = 0;
        for (Friend friend : friendList) {
            messageProcessResult = new MessageProcessResult<>();
            //logger.info("这是第" + index + "循环");
            fromUser = userMapper.getUserInfo(friend.getA_id());
            toUser = userMapper.getUserInfo(friend.getB_id());
            //logger.info("fromUs");
            logger.info("fromUser:" + fromUser.toString() + ", getA_id() = " + friend.getA_id() + ", toUser" + toUser.toString() + ", getB_id() = " + friend.getB_id());
            // 自己添加好友
            if (friend.getA_id() == user_id) {
                logger.info("自己添加好友，添加的好友id是 " + friend.getB_id());
                if (friend.getStatus() == Constant.ACCESS) {
                    processResult = "<b>" + toUser.getUserName() + "</b>接受了您的好友请求";
                } else if (friend.getStatus() == Constant.DENY) {
                    processResult = "<b>" + toUser.getUserName() + "</b>拒绝了您的好友请求";
                } else if (friend.getStatus() == Constant.UNPROCESSED) {
                    processResult = "您请求添加<b>" + toUser.getUserName() + "</b>为好友，正在等待对方处理";
                }
                logger.info("processResult: " + processResult);
            } else if (friend.getA_id() != user_id) {
                logger.info("别人添加自己为好友，别人的id是 " + friend.getA_id());
                //
                if (friend.getStatus() == Constant.ACCESS) {
                    processResult = "您接受了<b>" + fromUser.getUserName() + "</b>的好友请求";
                } else if (friend.getStatus() == Constant.DENY) {
                    processResult = "您拒绝了<b>" + fromUser.getUserName() + "</b>的好友请求";
                } else if (friend.getStatus() == Constant.UNPROCESSED) {
                    processResult = "<b>" + fromUser.getUserName() + "</b>请求添加您为好友";
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
            //index++;
        }
        logger.info("验证消息的处理结果是：" + messageProcessResultList.toString());
        return messageProcessResultList;
    }

}