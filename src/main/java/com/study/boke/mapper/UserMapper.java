package com.study.boke.mapper;

import com.study.boke.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(account_id,name,token,gmt_create,gmt_modified,avatar_url) VALUES(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl}) ")
    void insert(User user);

    @Select("SELECT * FROM user where token=#{token}")
    User findByCookie(String token);

    @Select("select * from user where id=#{id}")
    User getById(Integer id);

    @Select("select * from user where account_id=#{accountId}")
    User getByAccountId(String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User user);
}
