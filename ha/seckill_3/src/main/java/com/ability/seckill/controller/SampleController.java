package com.ability.seckill.controller;

import com.ability.seckill.model.User;
import com.ability.seckill.redis.RedisService;
import com.ability.seckill.redis.key.UserKey;
import com.ability.seckill.result.CodeMsg;
import com.ability.seckill.result.Result;
import com.ability.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (C): :
 * FileName: DemoController
 *
 * @author caobo
 * @create 2019-1-1 20:44
 * @since 1.0.0
 * Description:
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;

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
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){

        User user = new User();
        user.setId(1);
        user.setName("bert曹");
        redisService.set(UserKey.getById,""+1,user);//UserKey:id1
        return Result.success(true);
    }
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){

        User user = redisService.get(UserKey.getById,""+1,User.class);
        return Result.success(user);

    }
    @RequestMapping("/db/doGet")
    @ResponseBody
    public Result<User> doGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }
    @RequestMapping("/db/doTx")
    @ResponseBody
    public Result<Boolean> doTx(){
        userService.tx();
        return Result.success(true);
    }


}
