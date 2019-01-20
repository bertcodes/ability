package com.ability.seckill.dao;

import com.ability.seckill.model.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Copyright (C):
 * FileName: OrderDao
 *
 * @author caobo
 * @create 2019-1-4 11:03
 * @since 1.0.0
 * Description:
 */
@Mapper
public interface OrderDao {

    @Select("select * from seckill_order where user_id= #{userId} and goods_id=#{goodsId}")
    public SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") long goodsId);
}
