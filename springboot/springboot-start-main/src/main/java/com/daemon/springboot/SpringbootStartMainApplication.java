package com.daemon.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * Copyright (C): daemon©版权所有
 * FileName: SpringbootStartApplication
 *
 * @author caobo
 * @create 2018-11-17 20:37
 * @since 1.0.0
 * Description:
 *   自定义一个springboot  start
 *   1、新建一个项目
 *   2、提供一个配置类，配置需要装配出的类
 *   3、
 *      方法一：@EnableRedis注解@Import引入装配类
 *      方法二：META-INF/spring.factories 配置org.springframework.boot.autoconfigure.EnableAutoConfiguration转配类
 */

@SpringBootApplication
//@EnableRedis
public class SpringbootStartMainApplication {
    public static void main(String[] args) {

       ConfigurableApplicationContext context = SpringApplication.run(SpringbootStartMainApplication.class,args);
       Jedis jedis = context.getBean(Jedis.class);
       jedis.set("id","001");
       System.out.println(jedis.get("id"));
       context.close();
    }
}
