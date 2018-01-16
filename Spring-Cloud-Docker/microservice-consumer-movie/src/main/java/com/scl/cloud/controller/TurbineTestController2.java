package com.scl.cloud.controller;

import com.scl.cloud.feign.client.UserFeignClient;
import com.scl.cloud.feign.client.UserSpringMVCClient;
import com.scl.cloud.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author shengchenglong
 * @version 1.0
 * @since 17/9/8 16:50 JDK 1.8
 */
@Controller
public class TurbineTestController2 {

    @Autowired
    private UserFeignClient userFeignClient;
    @Resource
    private UserSpringMVCClient userSpringMVCClient;

    @GetMapping("turbinTest2/1")
    public User turbinTest1() {
        return userFeignClient.findById(1L);
    }

    @GetMapping("turbinTest2/2")
    public User turbinTest2() {
        return userSpringMVCClient.findById(1L);
    }
}
