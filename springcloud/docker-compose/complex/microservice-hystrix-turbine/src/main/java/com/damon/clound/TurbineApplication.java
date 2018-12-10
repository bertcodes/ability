package com.damon.clound;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: TurbineApplication
 *
 * @author caobo
 * @create 2018-11-6 12:50
 * @since 1.0.0
 * Description:
 */
@EnableTurbine
public class TurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
