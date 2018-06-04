package com.chat.mapper;

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
     * 查询所有用户
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select id, user_name as userName, age from user_info")
    List<UserTest> getUserAll();

    /**
     * 检查用户是否存在
     * @param user
     * @return
     */
    @Transactional(readOnly = true)
    @Select("select id, user_name as userName, password from user_info where user_name = #{user.userName} and password = #{user.password}")
    User getUserByUserNameAndPassword(@Param("user") User user);
}
