package com.damon.cloud.user.controller;

import com.damon.cloud.user.entity.User;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *
 * FileName: MovieController
 *
 * @author caobo
 * @create 2018-8-12 16:46
 * @since 1.0.0
 * Description:
 */
@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;;

    @GetMapping(value = "/user/{id}",produces = { "application/json;charset=UTF-8" })
    public User findById(@PathVariable Long id){
        return  this.restTemplate.getForObject("http://localhost:8000/"+id,User.class);
    }
    /**
     * 查询microservice-provider-user服务的信息并返回
     * @return microservice-provider-user服务的信息
     */
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("microservice-provider-user-my-metadata");
    }
}
