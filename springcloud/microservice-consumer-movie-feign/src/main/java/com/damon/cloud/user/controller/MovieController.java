package com.damon.cloud.user.controller;

import com.damon.cloud.user.entity.User;
import com.damon.cloud.user.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: MovieController
 *
 * @author caobo
 * @create 2018-8-12 16:46
 * @since 1.0.0
 * Description:
 */
@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping(value = "/user/{id}",produces = { "application/json;charset=UTF-8" })
    public User findById(@PathVariable Long id){
        return  this.userFeignClient.findById(id);
    }
}
