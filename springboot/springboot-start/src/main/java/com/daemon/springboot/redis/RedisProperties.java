package com.daemon.springboot.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright (C): daemon©版权所有
 * FileName: RedisProperties
 *
 * @author caobo
 * @create 2018-11-17 20:56
 * @since 1.0.0
 * Description:
 */
@Data
@ConfigurationProperties(prefix ="redis")
public class RedisProperties {

    private String host;//redis的ip地址

    private Integer port;//redis的端口号

}

