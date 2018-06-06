package com.chat.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author xiaolei hu
 * @date 2018/6/3 15:32
 **/
@Component
public class MyWebSocketHandler implements WebSocketHandler {
//    a.在afterConnectionEstablished连接建立成功之后，记录用户的连接标识，便于后面发信息，这里我是记录将id记录在Map集合中。
//
//    b.在handleTextMessage中可以对H5 Websocket的send方法进行处理
//
//    c.sendMessageToUser向指定用户发送消息，传入用户标识和消息体
//
//    d.sendMessageToAllUsers向左右用户广播消息，只需要传入消息体
//
//    e.handleTransportError连接出错处理，主要是关闭出错会话的连接，和删除在Map集合中的记录
//
//    f.afterConnectionClosed连接已关闭，移除在Map集合中的记录。
    // 日志
    private static final Logger logger = Logger.getLogger(MyWebSocketHandler.class);

    // 保存所有的用户session
    //private static final ArrayList<WebSocketSession> users = new ArrayList<>();

    // 保存所有的用户session
    private static final Map<Integer, WebSocketSession> users = new HashMap<>();

    // 用户标识
    private static final String CLIENT_ID = "user_id";
    
    // 连接就绪时
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("MyWebSocketHandler, connect websocket success.......");
        Integer user_id = getClientId(session);
        logger.info("获取的用户user_id:" + user_id);
        //System.out.println(user_id);
        if (user_id != null) {
            users.put(user_id, session);
            System.out.println("MyWebSocketHandler, user_id:" + user_id + ", " + session);
            logger.info("MyWebSocketHandler, user_id:" + user_id + ", " + session);
        }
        System.out.println("MyWebSocketHandler, connect websocket success......." + session);
        //users.add(session);
    }

    // 获取用户标识
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get(CLIENT_ID);
            return clientId;
        } catch (Exception e) {
            logger.info("MyWebSocketHandler, getClientId():" + e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }

    // 处理信息，可以对H5 Websocket的send方法进行处理
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message) throws Exception {
        Gson gson = new Gson();
        // 将消息JSON格式通过Gson转换成Map
        // message.getPayload().toString() 获取消息具体内容
        Map<String, Object> msg = gson.fromJson(message.getPayload().toString(), new TypeToken<Map<String, Object>>(){}.getType());
        logger.info("MyWebSocketHandler, handleMessage......." + message.getPayload() + "..........." + msg);
        System.out.println("MyWebSocketHandler, handleMessage......." + message.getPayload() + "..........." + msg);
        // 处理消息 msgContent消息内容
        //TextMessage textMessage = new TextMessage(msg.get("msgContent").toString(), true);
        // 调用方法（发送消息给所有人）
        //sendMsgToAllUsers(textMessage);
        webSocketSession.sendMessage(message);
        System.out.println("MyWebSocketHandler, handleMessage()" + message);
    }

    // 给指定用户发送消息
    public void sendMessageToUser(int clientId, WebSocketMessage<?> message) throws IOException {
        logger.info("当前在线用户：" + users.toString());
        if (users.get(clientId) == null) {
            logger.info("MyWebSocketHandler, 未获取到目的用户信息");
            System.out.println("MyWebSocketHandler, 未获取到目的用户信息");
            return;
        }
        WebSocketSession session = users.get(clientId);
        System.out.println("MyWebSocketHandler, sendMessge:" + session);
        if (!session.isOpen()) {
            return;
        }
        session.sendMessage(message);
        System.out.println("MyWebSocketHandler, message:" + message);
    }

    // 给所用用户发送消息
    public void sendMsgToAllUsers(WebSocketMessage<?> message) throws IOException {
//        for (WebSocketSession user : users) {
//            user.sendMessage(message);
//            System.out.println("user: " + user);
//            //user.
//        }
        Set<Integer> client_ids = users.keySet();
        WebSocketSession session;
        for (Integer client_id : client_ids) {
            session = users.get(client_id);
            if (session.isOpen()) {
                session.sendMessage(message);
                System.out.println("MyWebSocketHandler, message:" + message);
            }
        }
    }

    // 处理传输时候的异常
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        System.out.println("MyWebSocketHandler, 连接出错，关闭连接");
        // 移除session中的用户信息
        users.remove(getClientId(webSocketSession));
    }

    // 关闭连接时
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("MyWebSocketHandler, connect websocket closed.......");
        System.out.println("MyWebSocketHandler, connect websocket closed.......");
        //int clientId = (Integer) session.getAttributes().get(CLIENT_ID);
        users.remove(getClientId(session));
        logger.info("当前在线用户数：" + users.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
