package com.scl.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/10 15:21
 */
@RestController
public class DemoController {

    @GetMapping("index")
    public String index() {
        return "aaa";
    }
}
