package com.damon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: EurekaApplication
 *
 * @author caobo
 * @create 2018-8-12 18:38
 * @since 1.0.0
 * Description: 使用Eureka做服务发现.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
