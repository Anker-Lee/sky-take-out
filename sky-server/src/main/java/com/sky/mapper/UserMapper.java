package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid};")
    User getByOpenid(String openid);

    void insert(User user);

    @Select("select * from sky_take_out.user where id = #{userid}")
    User getById(Long userId);
}
