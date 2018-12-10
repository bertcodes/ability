package com.damon.clound.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: ConfigClientController
 *
 * @author caobo
 * @create 2018-11-4 21:18
 * @since 1.0.0
 * Description:
 */
@RestController
public class ConfigClientController {
    @Value("${profile}")
    private String profile;

    @GetMapping(name = "getProfile")
    public String getProfile(){
        return  this.profile;
    }
}
