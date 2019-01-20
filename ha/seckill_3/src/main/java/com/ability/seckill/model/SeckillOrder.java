package com.ability.seckill.model;

/**
 * Copyright (C):
 * FileName: SeckillOrder
 *
 * @author caobo
 * @create 2019-1-4 10:12
 * @since 1.0.0
 * Description:
 */
public class SeckillOrder {

    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
