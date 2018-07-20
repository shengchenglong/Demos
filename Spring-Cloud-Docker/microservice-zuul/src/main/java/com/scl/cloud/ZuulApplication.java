package com.scl.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 * @EnableZuulProxy, 声明一个zuul代理。
 * 1. 该代理使用Ribbon来定位注册在Eureka Server中的微服务
 * 2. 同时整合了Hystrix，从而实现了容错，所有的Zuul请求都会在Hystrix命令中执行
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}
