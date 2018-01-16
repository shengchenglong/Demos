package com.scl.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scl.cloud.feign.client.UserSpringMVCClient;
import com.scl.cloud.pojo.User;
import com.scl.cloud.feign.client.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
//import org.springframework.web.client.RestTemplate;


/**
 * @author shengchenglong
 * @version 1.0
 * @since 17/8/15 09:49 JDK} 1.8
 */
@RestController
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//
//    @GetMapping("/user/{id}")
//    public User findByUserId(@PathVariable Long id) {
//        return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
//    }
//
//    @GetMapping("/user-instance")
//    public List<ServiceInstance> showInfo() {
//        return this.discoveryClient.getInstances("microservice-provider-user");
//    }
//
//    @GetMapping("/log-instance")
//    public void logUserInstance() {
//        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
//        LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
//    }

    @Autowired
    private UserFeignClient userFeignClient;

    @Resource
    private UserSpringMVCClient userSpringMVCClient;

    @GetMapping("user/{id}")
    public User findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

    @PostMapping("findByIdAndName")
    public List<User> findByIdMvc(@RequestParam("id") Long id, @RequestParam("username") String username) {
        return this.userSpringMVCClient.findByIdAndName(id, username);
    }

    @PostMapping("findByExample")
    public List<User> findByExample(@RequestBody User user) {
        return this.userSpringMVCClient.findByExample(user);
    }


    /**
     * fallbackMethod: 回退方法（相同的返回值，参数类型）
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "findByIdFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3")
    },threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "5")
    })
    @GetMapping("mvc/user/{id}")
    public User findByIdMvc(@PathVariable("id") Long id) {
        return this.userSpringMVCClient.findById(id);
    }

    // 回退方法
    private User findByIdFallBack(Long id) {
        User u = new User();
        u.setUsername("back");
        return u;
    }




}
