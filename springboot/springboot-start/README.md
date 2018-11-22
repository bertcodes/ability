springboot-start：start模块

springboot-start-main：主项目

自定义一个springboot start 步骤：

1、新建一个项目

2、提供一个配置类，配置需要装配出的类

3、使能需加载的start模块

  方法一：@EnableRedis注解@Import引入装配类
  
  方法二：META-INF/spring.factories 配置org.springframework.boot.autoconfigure.EnableAutoConfiguration装配类
