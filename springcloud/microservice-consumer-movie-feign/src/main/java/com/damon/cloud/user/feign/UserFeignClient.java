package com.damon.cloud.user.feign;

import com.damon.cloud.user.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Copyright (C): 恒大集团©版权所有 Evergrande Group
 * FileName: UserFeignClient
 *
 * @author caobo
 * @create 2018-11-3 17:04
 * @since 1.0.0
 * Description:
 */
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
