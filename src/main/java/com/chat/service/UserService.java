package com.chat.service;

import com.chat.model.User;
import com.chat.model.UserTest;

import java.util.List;

/**
 * @author xiaolei hu
 * @date 2018/6/4 19:11
 **/
public interface UserService {

    /**
     * 查询所有用户
     * @return
     */
    List<UserTest> getUserAll();

    /**
     * 检查用户名和密码是否正确
     * @param user
     * @return
     */
    User getUserByUserNameAndPassword(User user);
}
