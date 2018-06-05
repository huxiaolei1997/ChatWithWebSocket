package com.chat.mapper;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.model.UserTest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaolei hu
 * @date 2018/6/4 19:06
 **/
public interface UserMapper {

    /**
     * 查询用户好友
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select " +
            "id, " +
            "user_name as userName, " +
            "password " +
            "from " +
            "user_info " +
            "where " +
            "id " +
            "in (" +
            "select " +
            "b_id " +
            "from " +
            "friends " +
            "where " +
            "a_id = #{user_id} " +
            "union " +
            "select " +
            "a_id " +
            "from " +
            "friends " +
            "where " +
            "b_id = #{user_id})")
    List<User> getUserAllFriends(@Param("user_id") int user_id);

    /**
     * 检查用户是否存在
     * @param user
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select id, user_name as userName, password from user_info where user_name = #{user.userName} and password = #{user.password}")
    User getUserByUserNameAndPassword(@Param("user") User user);

    /**
     * 获取和指定用户的聊天记录
     * @return
     */
    @Select("select " +
            "id, " +
            "to_user_id, " +
            "from_user_id, " +
            "content, " +
            "send_time " +
            "from " +
            "message " +
            "where " +
            "(to_user_id = #{message.to_user_id} " +
            "and " +
            "from_user_id = #{message.from_user_id}) or " +
            "(to_user_id = #{message.from_user_id} " +
            "and " +
            "from_user_id = #{message.to_user_id})")
    List<Message> getMessageRecord(@Param("message") Message message);
}
