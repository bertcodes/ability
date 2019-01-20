package com.ability.seckill.dao;

import com.ability.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select g.*,sg.seckill_price,sg.stock_count,sg.start_date,sg.end_date from seckill_goods sg LEFT JOIN goods g ON sg.goods_id = g.id")
    List<GoodsVo> listGoodsVo();

    @Select("select g.*,sg.seckill_price,sg.stock_count,sg.start_date,sg.end_date from seckill_goods sg LEFT JOIN goods g ON sg.goods_id = g.id where sg.goods_id=#{goodsId}")
    GoodsVo getGoodsVoByGoodsId(long goodsId);
}
