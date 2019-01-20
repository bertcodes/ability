package com.ability.seckill.service;

import com.ability.seckill.dao.OrderDao;
import com.ability.seckill.model.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C):
 * FileName: OrderService
 *
 * @author caobo
 * @create 2019-1-4 11:01
 * @since 1.0.0
 * Description:
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    public SeckillOrder getSeckillOrderByUserIdGoodsId(Long userId, long goodsId) {
        return orderDao.getSeckillOrderByUserIdGoodsId(userId,goodsId);
    }

}
