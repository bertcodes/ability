package com.damon.clound;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 *
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
