package com.chat.serviceImpl;

import com.chat.model.Pager;
import com.chat.model.User;
import com.chat.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:springconfig/spring-service.xml", "classpath:springconfig/spring-dao.xml"})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findUserByUserName() {
        Pager<User> userPager = userService.findUserByUserName(1, 10, "test", 105);
        System.out.println("total_record = " + userPager.getTotal_record());
    }
}