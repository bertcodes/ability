package com.damon.cloud.controller;

import com.damon.cloud.entity.User;
import com.damon.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: UserController
 *
 * @author caobo
 * @create 2018-8-12 14:22
 * @since 1.0.0
 * Description:
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/{id}",produces = { "application/json;charset=UTF-8" })
    public User findById(@PathVariable Long id){
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }

}
