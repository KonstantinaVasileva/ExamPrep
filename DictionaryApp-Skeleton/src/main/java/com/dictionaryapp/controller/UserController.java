package com.dictionaryapp.controller;

import com.dictionaryapp.service.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final CurrentUser currentUser;

    public UserController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        if (currentUser.isLoggedIn()){
            return "redirect:/";
        }
        return "login";
    }

//    @PostMapping("/register")
//    public String register(User user) {}

}
