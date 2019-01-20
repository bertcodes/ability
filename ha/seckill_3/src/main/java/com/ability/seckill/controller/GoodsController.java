package com.ability.seckill.controller;

import com.ability.seckill.model.SeckillUser;
import com.ability.seckill.service.GoodsService;
import com.ability.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Copyright (C): :
 * FileName: GoodsController
 *
 * @author caobo
 * @create 2019-1-3 17:00
 * @since 1.0.0
 * Description:
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/toList")
    public String list(Model model, SeckillUser user){
        model.addAttribute("user",user);
       List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String detail(Model model, SeckillUser user
    , @PathVariable("goodsId")long goodsId){
        model.addAttribute("user",user);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods",goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        
        int seckillStatus = 0;
        int remainSeconds = 0;

        if(now < startAt){//秒杀还没开始，倒计时
            seckillStatus = 0;
            remainSeconds = (int) ((startAt-now)/1000);
        }else if(now > endAt){//秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        }else{
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        return "goods_detail";
    }

}
