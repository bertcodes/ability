package com.ability.seckill.dao;

import com.ability.seckill.model.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Copyright (C): :
 * FileName: SeckillUserDao
 *
 * @author caobo
 * @create 2019-1-3 14:06
 * @since 1.0.0
 * Description:
 */
@Mapper
public interface SeckillUserDao {

    @Select("select * from seckill_user where id = #{id}")
    public SeckillUser getById(@Param("id")long id);

}
