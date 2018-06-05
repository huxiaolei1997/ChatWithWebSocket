package com.chat.mapper;

import com.chat.model.Message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiaolei hu
 * @date 2018/6/5 22:12
 **/
public interface MessageMapper {

    /**
     * 保存聊天记录到数据库中
     * @param message
     */
    @Select("insert " +
            "into " +
            "message" +
            "(" +
            "from_user_id, " +
            "to_user_id, " +
            "content, " +
            "send_time" +
            ") " +
            "values" +
            "(" +
            "#{message.from_user_id}, " +
            "#{message.to_user_id}, " +
            "#{message.content}, " +
            "#{message.send_time}" +
            ")")
    void saveChatRecord(@Param("message") Message message);
}
