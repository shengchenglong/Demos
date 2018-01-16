package com.scl.cloud.feign;

import feign.Contract;
import feign.Logger;
import feign.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;

/**
 * 该类为Feign的配置类
 * 注意：该类不应该在主程序上下文的@ComponentScan中（会被所有FeignClient所共享,以下配置的Bean都会生效）
 *
 * @author shengchenglong
 * @version 1.0
 * @since 17/8/29 15:11 JDK 1.8
 */
@Configuration
public class FeignConfiguration {

    /**
     * 将契约改为feign的原生契约
     * @return
     */
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

//    @Bean
//    public BasicAuthorizationInterceptor basicAuthorizationInterceptor() {
//        return new BasicAuthorizationInterceptor("user", "password");
//    }


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }


}
