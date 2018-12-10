package com.damon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: RibbonConfiguration
 *
 * @author caobo
 * @create 2018-9-5 23:42
 * @since 1.0.0
 * Description:该类为Ribbon的配置类
 * 注意：该类不应该在主应用程序上下文的@componentScan中
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule(){
        //负载均衡规则，改为随机
        return new RandomRule();
    }
}
