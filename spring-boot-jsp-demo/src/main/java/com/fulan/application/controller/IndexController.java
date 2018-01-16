package com.fulan.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/11 12:52
 */
@Controller
public class IndexController {


    @GetMapping("/")
    public String demo(Model model) {
        model.addAttribute("time", new Date());

        return "demo";
    }
}  