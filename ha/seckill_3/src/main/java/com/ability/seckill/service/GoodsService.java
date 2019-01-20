package com.ability.seckill.service;

import com.ability.seckill.dao.GoodsDao;
import com.ability.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C):
 * FileName: GoodsService
 *
 * @author caobo
 * @create 2019-1-4 11:01
 * @since 1.0.0
 * Description:
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return  goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

}
