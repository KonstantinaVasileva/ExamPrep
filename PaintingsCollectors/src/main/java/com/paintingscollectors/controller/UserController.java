package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.RegisterUserDTO;
import com.paintingscollectors.service.LoggedInUser;
import com.paintingscollectors.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final LoggedInUser loggedInUser;
    private final UserService userService;

    public UserController(LoggedInUser loggedInUser, UserService userService) {
        this.loggedInUser = loggedInUser;
        this.userService = userService;
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (loggedInUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login() {

        if (loggedInUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserDTO registerUserDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("registerUserDTO", "confirmPassword", "Passwords do not match")
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUserDTO", bindingResult);
            return "redirect:/register";
        }

        userService.userRegister(registerUserDTO);

        return "redirect:/login";
    }


    @PostMapping("/login")
    public String loginUser() {
//        userService.login()
        return "redirect:/home";
    }

}
