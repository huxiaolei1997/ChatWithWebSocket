package com.chat.serviceImpl;

import com.chat.mapper.UserMapper;
import com.chat.model.User;
import com.chat.model.UserTest;
import com.chat.service.UserService;
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
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserTest> getUserAll() {
        List<UserTest> userTestList = userMapper.getUserAll();
        return userTestList;
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
}
