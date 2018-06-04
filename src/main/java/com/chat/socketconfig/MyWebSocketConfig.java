package com.chat.socketconfig;

import com.chat.interceptor.WebSocketInterceptor;
import com.chat.service.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * @author xiaolei hu
 * @date 2018/6/3 15:53
 **/
@Configuration
@EnableWebMvc
@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    //@Autowired
    //private WebSocketHandler myWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //前台 可以使用websocket环境
        registry.addHandler(myWebSocketHandler(),"/websocket").addInterceptors(new WebSocketInterceptor());

        //前台 不可以使用websocket环境，则使用sockjs进行模拟连接
        registry.addHandler(myWebSocketHandler(), "/sockjs/websocket").addInterceptors(new WebSocketInterceptor())
                .withSockJS();
    }

    // websocket 处理类
    @Bean
    public WebSocketHandler myWebSocketHandler(){
        return new MyWebSocketHandler();
    }
}
