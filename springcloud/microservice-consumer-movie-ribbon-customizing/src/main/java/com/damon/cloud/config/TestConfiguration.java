package com.damon.cloud.config;

import com.damon.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: TestConfiguration
 *
 * @author caobo
 * @create 2018-9-5 23:47
 * @since 1.0.0
 * Description:使用RibbonClient,为特定name的Ribbon Client自定义配置
 * 使用@RibbonClient的configuration属性，指定Ribbon的配置类
 */
@Configuration
@RibbonClient(name = "microservice-provider-user",configuration = RibbonConfiguration.class)
public class TestConfiguration {

}
