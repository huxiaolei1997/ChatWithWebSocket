package com.chat.aspect;

import org.springframework.stereotype.Service;

/**
 * 测试类
 * @author xiaolei hu
 * @date 2018/6/7 15:53
 **/
@Service
public class TestAop {
    public void test() {
        System.out.println("test方法");
    }
}
