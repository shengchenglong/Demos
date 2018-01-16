package com.scl.cloud.controller.rest;

import com.scl.cloud.dao.UserRepository;
import com.scl.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("findByIdAndName")
    public List findByIdAndName(@RequestParam("id")Long id, @RequestParam("username") String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    @PostMapping("findByExample")
    List<User> findByExample(@RequestBody User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

}
