package com.daemon.springboot.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Copyright (C): daemon版权所有
 * FileName: RedisAutoConfiguration
 *
 * @author caobo
 * @create 2018-11-17 21:02
 * @since 1.0.0
 * Description:
 */
@Configuration
@ConditionalOnClass(Jedis.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis(RedisProperties redisProperties){
        return new Jedis(redisProperties.getHost(),redisProperties.getPort());
    }

}
