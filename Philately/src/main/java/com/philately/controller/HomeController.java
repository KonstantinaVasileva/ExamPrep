package com.philately.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogout() {
        return "index";
    }

    @GetMapping("/home")
    public String homeLogin() {
        return "home";
    }

}
