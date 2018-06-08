package com.chat.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 消息实体类
 * @author xiaolei hu
 * @date 2018/6/4 16:26
 **/
public class Message implements Serializable {
    // 消息id
    private long id;

    // 发信者id
    private int from_user_id;

    // 收信者id
    private int to_user_id;

    // 消息主体内容
    private String content;

    // 消息类型 （0 表示普通消息，1 表示验证消息）
    private int message_type;

    // 发送时间
    private Timestamp send_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public Timestamp getSend_time() {
        return send_time;
    }

    public void setSend_time(Timestamp send_time) {
        this.send_time = send_time;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"from_user_id\":" + from_user_id +
                ", \"to_user_id\":" + to_user_id +
                ", \"content\":\"" + content + "\"" +
                ", \"message_type\":" + message_type +
                ", \"send_time\":" + send_time.getTime() +
                '}';
    }
}
