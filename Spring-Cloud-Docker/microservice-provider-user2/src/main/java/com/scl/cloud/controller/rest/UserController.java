package com.scl.cloud.controller.rest;

import com.scl.cloud.dao.UserRepository;
import com.scl.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shengchenglong
 * @version 1.0
 * @since 17/8/14 15:01 JDK} 1.8
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        User user = userRepository.findOne(id);
        return user;
    }

}
