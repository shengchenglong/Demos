package com.scl.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shengchenglong
 * @Date: 2017/12/28 09:41
 */
@Controller
public class DemoController {

    @GetMapping("/index")
    public String index() {
        return "home";
    }

}
