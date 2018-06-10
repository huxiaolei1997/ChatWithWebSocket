package com.chat.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 测试类
 * @author xiaolei hu
 * @date 2018/6/7 15:52
 **/
@Aspect
@Component
public class Aspect1 {
    @Pointcut(value = "execution(* com.chat.aspect.TestAop.*(..))")
    public void aopDemo() {

    }

    @Before("aopDemo()")
    public void before(JoinPoint joinPoint) {
        System.out.println("Aspect");
    }
}
