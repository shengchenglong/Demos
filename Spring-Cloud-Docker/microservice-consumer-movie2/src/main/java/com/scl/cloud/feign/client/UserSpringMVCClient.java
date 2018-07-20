package com.scl.cloud.feign.client;

import com.scl.cloud.feign.FeignConfiguration;
import com.scl.cloud.pojo.User;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 使用SpringMVC注解的FiegnClient
 *
 * @author shengchenglong
 * @version 1.0
 * @since 17/9/6 10:06 JDK 1.8
 */

// 启动报错：Caused by: java.lang.IllegalStateException: Method getById not annotated with HTTP method type (ex. GET, POST)
// 是因为手动写了一个配置类FeignConfiguration，
// 该类在Spring上下文中，会被所有FeignClient共享
// 将其UserFeignClient 和 FiegnCinfiguration注掉 来测试
@FeignClient(name = "microservice-provider-user")
public interface UserSpringMVCClient {

    /**
     * 参数在请求uri中
     * 1. @PathVariable("id")中必须指定id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    User findById(@PathVariable("id") Long id);

    /**
     *  参数
     *  可以在uri中；
     *  也可以在requestBody中
     *      此时分两种：1. contentType=application/json 则参数：{"id" : "1" , "username" : "aaa"}
     *                2. contentType＝application/x-www-form-urlencoded 则参数：id=1&username=aaa
     *
     *  @RequestParam("id")中必须指定id
     * @param id
     * @param username
     * @return
     */
    @PostMapping("findByIdAndName")
    List<User> findByIdAndName(@RequestParam("id") Long id, @RequestParam("username") String username);

    /**
     * 参数在requestBody中
     * 1. 这里要求请求body必须是json格式的参数，contentType是application/json
     * @param user
     * @return
     */
    @PostMapping("findByExample")
    List<User> findByExample(@RequestBody User user);

}
