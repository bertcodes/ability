package com.ability.seckill.controller;

import com.ability.seckill.model.SeckillUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/toList")
    public String list(Model model, SeckillUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }

}
