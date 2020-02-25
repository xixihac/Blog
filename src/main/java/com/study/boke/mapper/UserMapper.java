package com.study.boke.mapper;

import com.study.boke.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(account_id,name,token,gmt_create,gmt_modified,avatar_url) VALUES(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl}) ")
    void insert(User user);

    @Select("SELECT * FROM user where token=#{token}")
    User findByCookie(String token);

    @Select("select * from user where id=#{id}")
    User getById(Integer id);
}
