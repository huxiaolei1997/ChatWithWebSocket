package com.chat.mapper;

import com.chat.model.Friend;
import com.chat.model.Message;
import com.chat.model.User;
import com.chat.model.UserTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaolei hu
 * @date 2018/6/4 19:06
 **/
public interface UserMapper {

    /**
     * 获取用户信息
     * @param user_id
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select id, user_name as userName, age from user_info where id = #{user_id}")
    User getUserInfo(@Param("user_id") int user_id);

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
            "and " +
            "status = #{status} " +
            "union " +
            "select " +
            "a_id " +
            "from " +
            "friends " +
            "where " +
            "b_id = #{user_id} " +
            "and " +
            "status = #{status})")
    List<User> getUserAllFriends(@Param("user_id") int user_id, @Param("status") int status);

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
    @Transactional
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
            "from_user_id = #{message.to_user_id}) " +
            "order by send_time asc")
    List<Message> getMessageRecord(@Param("message") Message message);

    /**
     * 检查用户名是否已经存在
     * @param userName
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select count(id) from user_info where user_name = #{userName}")
    long checkUserIfExist(@Param("userName") String userName);

    /**
     * 保存注册的用户到数据库中
     * @param user
     */
    @Transactional
    @Insert("insert into user_info(user_name, password) values(#{user.userName}, #{user.password})")
    void saveRegisterUser(@Param("user") User user);

    /**
     * 根据用户名查找用户（不显示自己）
     * @param userName
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select " +
            "id, " +
            "user_name as userName " +
            "from " +
            "user_info " +
            "where " +
            "user_name " +
            "like " +
            "concat(concat('%', #{userName}), '%') " +
            "and " +
            "id != #{user_id}")
    List<User> findUserByUserName(@Param("userName") String userName, @Param("user_id") int user_id);

    /**
     * 添加好友请求到数据库中
     * @param message
     */
    @Transactional
    @Insert("insert into friends(a_id, b_id) values(#{message.from_user_id}, #{message.to_user_id})")
    void saveFriendRequest(@Param("message") Message message);

    /**
     * 处理好友请求
     * @param friend
     */
    @Transactional
    @Update("update friends set status = #{friend.status} where a_id = #{friend.a_id} and b_id = #{friend.b_id}")
    void processUserRequest(@Param("friend") Friend friend);

    /**
     * 根据 user_id 获取和该用户有关的好友验证请求
     * @param user_id
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select id, a_id, b_id, status, add_time from friends where a_id = #{user_id} or b_id = #{user_id} order by add_time desc")
    List<Friend> getUserRequestByUserId(@Param("user_id") int user_id);
}
