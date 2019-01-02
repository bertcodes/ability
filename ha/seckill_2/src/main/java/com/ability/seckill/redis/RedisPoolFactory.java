package com.ability.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Copyright (C): Bert©版权所有
 * FileName: RedisPoolFactory
 *
 * @author caobo
 * @create 2019-1-1 23:24
 * @since 1.0.0
 * Description:
 */
@Service
public class RedisPoolFactory {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public JedisPool JedisPoolFactory(){

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdel());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);

        JedisPool jp = new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),
        redisConfig.getTimeout()*1000,redisConfig.getPassword(),0);
        return jp;
    }

}
