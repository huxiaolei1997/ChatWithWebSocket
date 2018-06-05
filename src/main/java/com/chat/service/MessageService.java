package com.chat.service;

import com.chat.model.Message;

/**
 * @author xiaolei hu
 * @date 2018/6/5 22:16
 **/
public interface MessageService {

    /**
     * 保存聊天记录到数据库中
     * @param message
     */
    void saveChatRecord(Message message);
}
