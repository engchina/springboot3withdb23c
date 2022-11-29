package com.oracle.db23c.mapper;

import com.oracle.db23c.bo.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select("select username, password from myuser where username = #{username}")
    @Results(id = "finduser",
            value = {
                    @Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password")
            })
    User findByUsername(String username);

    @Insert("insert into myuser(username, password) values (#{username}, #{password})")
    @ResultType(Integer.class)
    Integer saveUser(User user);
}
