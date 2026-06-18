package com.example.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * 登录后的主导航页
     */
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}