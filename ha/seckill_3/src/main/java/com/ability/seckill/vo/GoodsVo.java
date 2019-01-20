package com.ability.seckill.vo;

import com.ability.seckill.model.Goods;

import java.util.Date;

/**
 * Copyright (C):
 * FileName: GoodsVo
 *
 * @author caobo
 * @create 2019-1-4 10:56
 * @since 1.0.0
 * Description:
 */
public class GoodsVo extends Goods {

    private Integer stockCount;
    private Double seckillPrice;
    private Date startDate;
    private Date endDate;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
