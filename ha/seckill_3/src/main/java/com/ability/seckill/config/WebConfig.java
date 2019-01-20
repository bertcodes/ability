package com.ability.seckill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Copyright (C):
 * FileName: WebConfig
 *
 * @author caobo
 * @create 2019-1-3 17:49
 * @since 1.0.0
 * Description:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

}
