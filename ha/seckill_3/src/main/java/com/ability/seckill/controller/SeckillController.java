package com.ability.seckill.controller;

import com.ability.seckill.model.Goods;
import com.ability.seckill.model.SeckillOrder;
import com.ability.seckill.model.SeckillUser;
import com.ability.seckill.result.CodeMsg;
import com.ability.seckill.service.GoodsService;
import com.ability.seckill.service.OrderService;
import com.ability.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Copyright (C):
 * FileName: SeckillController
 *
 * @author caobo
 * @create 2019-1-4 16:10
 * @since 1.0.0
 * Description:
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/doSeckill")
    public String seckill(Model model, SeckillUser user
            , @RequestParam("goodsId")long goodsId){
        if(user == null){
            return "login";
        }
        //判断库存
        GoodsVo goods= goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <0 ){
            model.addAttribute("errmsg", CodeMsg.SECKILL_OVER);
            return "seckill_fail";
        }
        //判断是否重复秒杀
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(),goodsId);


        return "order_detail";
    }
}
