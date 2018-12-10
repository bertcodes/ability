package com.damon.clound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: ConfigServerApplication
 *
 * @author caobo
 * @create 2018-11-4 18:11
 * @since 1.0.0
 * Description:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
