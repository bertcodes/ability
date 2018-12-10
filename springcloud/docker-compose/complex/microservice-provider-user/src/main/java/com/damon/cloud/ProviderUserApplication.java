package com.damon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: ProviderUserApplication
 *
 * @author caobo
 * @create 2018-8-12 14:25
 * @since 1.0.0
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApplication.class);
    }
}
