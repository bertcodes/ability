package com.ability.seckill.controller;

import com.ability.seckill.result.CodeMsg;
import com.ability.seckill.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (C): Bert©版权所有
 * FileName: DemoController
 *
 * @author caobo
 * @create 2019-1-1 20:44
 * @since 1.0.0
 * Description:
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "Hello World.";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello bert.");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/themeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","bert");
        return "hello";
    }



}
