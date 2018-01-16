package com.scl.cloud.feign.fallback.factory;

import com.scl.cloud.feign.client.UserFeignClient;
import com.scl.cloud.pojo.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用FallBack Factory来检查回退原因
 *
 * @author shengchenglong
 * @version 1.0
 * @since 17/9/8 09:30 JDK 1.8
 */
@Component
public class UserFeignClientFallBackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignClientFallBackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                LOGGER.info("进入回调；原因是：", throwable);
                User user = new User();
                user.setId(-1L);
                user.setUsername("默认用户");
                return user;
            }
        };
    }
}
