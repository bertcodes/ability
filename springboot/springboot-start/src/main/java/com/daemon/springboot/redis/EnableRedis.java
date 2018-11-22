package com.daemon.springboot.redis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright (C): daemon©版权所有
 * FileName: EnableRedis
 *
 * @author caobo
 * @create 2018-11-17 21:45
 * @since 1.0.0
 * Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisAutoConfiguration.class)
public @interface EnableRedis {

}
