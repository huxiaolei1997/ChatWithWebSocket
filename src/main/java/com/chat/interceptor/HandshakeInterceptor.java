package com.chat.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author xiaolei hu
 * @date 2018/6/3 15:48
 **/
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    // 握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            System.out.println("++++++++++++++++ HandshakeInterceptor: beforeHandshake  ++++++++++++++" + attributes);
            HttpSession session = ((ServletServerHttpRequest) request).getServletRequest().getSession();
            if (session != null) {
                attributes.put("user_id", session.getAttribute("user_id"));
                System.out.println("HandshakeInterceptor" + session.getAttribute("user_id"));
            }
        }
        return true;
    }


    // 握手后
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("++++++++++++++++ HandshakeInterceptor: afterHandshake  ++++++++++++++");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
