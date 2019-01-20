package com.ability.seckill.dao;

import com.ability.seckill.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Copyright (C): :
 * FileName: UserDao
 *
 * @author caobo
 * @create 2019-1-2 14:07
 * @since 1.0.0
 * Description:
 */
@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user(id,name)values(#{id},#{name})")
    public int insert(User user);

}
