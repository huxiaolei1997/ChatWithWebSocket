package com.chat.aspect;

import com.chat.controller.MessageController;
import com.chat.model.Message;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiaolei hu
 * @date 2018/6/7 16:05
 **/
public class TestTestAop {

    @Test
    public void test1() {
        String springXmlPath = "classpath:springconfig/SpringAopTest.xml";
        // 非web应用使用AbstractApplicationContext初始化bean容器
        // 在非Web应用中，手工加载Spring IoC容器，不能用ApplicationContext，
        // 要用AbstractApplicationContext。用完以后要记得调用ctx.close()关闭容器。
        // 如果不记得关闭容器，最典型的问题就是数据库连接不能释放。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(springXmlPath);
        //applicationContext.get
        TestAop testAop = applicationContext.getBean("testAop", TestAop.class);
        //MessageController messageController = applicationContext.getBean("messageController", MessageController.class);
        //JsrService jsrService = abstractApplicationContext.getBean("jsrService", JsrService.class);
        //jsrService.save();
        //biz.biz();
        testAop.test();
        //messageController.messageController();
        //System.out.println(myDriverManager.getClass().getName());
        //abstractApplicationContext.close();
    }
}