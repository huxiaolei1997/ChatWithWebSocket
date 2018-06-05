package com.chat.serviceImpl;

import com.chat.mapper.MessageMapper;
import com.chat.model.Message;
import com.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaolei hu
 * @date 2018/6/5 22:17
 **/
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 保存聊天记录到数据库中
     * @param message
     */
    @Override
    public void saveChatRecord(Message message) {
        messageMapper.saveChatRecord(message);
    }
}
