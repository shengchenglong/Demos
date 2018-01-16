package com.scl.cloud.feign.client;

import com.scl.cloud.feign.FeignConfiguration;
import com.scl.cloud.feign.fallback.UserFeignClientFallBack;
import com.scl.cloud.feign.fallback.factory.UserFeignClientFallBackFactory;
import com.scl.cloud.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用Feign自己的注解的 FeignClient
 * @author shengchenglong<br/>
 * @date 2017年8月29日 下午1:27:28<br/>
 * @version 
 * @since JDK 1.8
 */

// @FeignClient注解的name是一个任意的客户端名称，用于创建Ribbon负载均衡器
// 在本例中，使用了Eureka，则Ribbon会把microservice-provider-user解析成Eureka Server服务注册表中的服务

//@FeignClient(name = "microservice-provider-user")
// 1.自定义Feign配置（这里的configuration不写也会是该值，因为FeignConfiguration在Spring的上下文中）
// 2.为Feign添加Hystrix的回退方法，使用fallback属性
// 3.通过FallbackFactory来检查回退原因
// 4. FallBack 和 FallBackFactory 存在一个即可
@FeignClient(name = "microservice-provider-user"
// ,configuration = FeignConfiguration.class)
//,fallback = UserFeignClientFallBack.class)
,fallbackFactory = UserFeignClientFallBackFactory.class)
//)

// 还可以使用url属性指定请求URL
//@FeignClient(name = "microservice-provider-user", url = "http://localhost:9999/")
public interface UserFeignClient {

//	使用Feign自带的注解
//	@RequestLine("GET /{id}")
//	public User findById(@Param("id") Long id);

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id);
}
