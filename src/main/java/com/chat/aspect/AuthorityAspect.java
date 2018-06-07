package com.chat.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 页面权限切面类
 * @author xiaolei hu
 * @date 2018/6/7 20:39
 **/
@Aspect
@Component
public class AuthorityAspect {
    private final static Logger logger = Logger.getLogger(AuthorityAspect.class);

    @Pointcut(value = "execution(* com.chat.controller.ChatController.*(..))")
    public void checkUserIfLogin() {
        //System.out.println("切面类");
    }

    @Before("checkUserIfLogin()")
    public void before(JoinPoint joinPoint) {
        //System.out.println(111111);
        System.out.println("doBefore");
        logger.info("doBefore");
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        // 获取当前登录用户的id，用来检查用户是否登录
        //Integer user_id = (int) ;
        Object user_id = session.getAttribute("user_id");
        if (user_id == null) {
            logger.info("用户未登录，请先登录！");
            throw new RuntimeException("用户未登录");
            //return;
        }
    }

}
