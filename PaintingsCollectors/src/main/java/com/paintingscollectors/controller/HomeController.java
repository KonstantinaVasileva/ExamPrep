package com.paintingscollectors.controller;

import com.paintingscollectors.service.LoggedInUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final LoggedInUser loggedInUser;

    public HomeController(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @GetMapping("/")
    public String homeIndex() {
        if (loggedInUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        if (!loggedInUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "home";
    }



}
