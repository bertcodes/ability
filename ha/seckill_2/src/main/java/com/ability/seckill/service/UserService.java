package com.ability.seckill.service;

import com.ability.seckill.dao.UserDao;
import com.ability.seckill.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C): :
 * FileName: UserService
 *
 * @author caobo
 * @create 2019-1-2 14:04
 * @since 1.0.0
 * Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {


        User user2 = new User();
        user2.setId(2);
        user2.setName("Eelna 徐");
        userDao.insert(user2);

        User user1 = new User();
        user1.setId(1);
        user1.setName("damon 张");
        userDao.insert(user1);


        return true;
    }
}
