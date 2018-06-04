package com.chat.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author xiaolei hu
 * @date 2018/6/3 15:48
 **/
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor implements HandshakeInterceptor {
    // 握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            System.out.println("++++++++++++++++ WebSocketInterceptor: beforeHandshake  ++++++++++++++" + attributes);
            HttpSession session = ((ServletServerHttpRequest) request).getServletRequest().getSession();
            // 将属性传递给目标WebSocketHandler
            // 复制attributes属性（一般从HTTP session中获取）到WebSocketSessioin中，使其可以通过webSocketSession.getAttributes()获取
            if (session != null) {
                attributes.put("user_id", session.getAttribute("user_id"));
                System.out.println("WebSocketInterceptor" + session.getAttribute("user_id"));
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }


    // 握手后
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("++++++++++++++++ WebSocketInterceptor: afterHandshake  ++++++++++++++");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
