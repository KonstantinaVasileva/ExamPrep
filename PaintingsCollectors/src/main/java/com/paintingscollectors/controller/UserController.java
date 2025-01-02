package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.LoginUserDTO;
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

    @ModelAttribute("loginUserDTO")
    public LoginUserDTO loginUserDTO() {
        return new LoginUserDTO();
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

        boolean register = userService.userRegister(registerUserDTO);
        if (bindingResult.hasErrors() || !register) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUserDTO", bindingResult);
            return "redirect:/register";
        }

        userService.register(registerUserDTO);

        return "redirect:/login";
    }


    @PostMapping("/login")
    public String loginUser(@Valid LoginUserDTO loginUserDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (loggedInUser.isLoggedIn()) {
            return "redirect:/home";
        }

        boolean login = userService.login(loginUserDTO);

        if (!login){
            bindingResult.addError(
                    new FieldError("loginUserDTO", "password", "Invalid username or password")
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginUserDTO", bindingResult);

            return "redirect:/login";
        }

        loggedInUser.login(loginUserDTO.getUsername());

        return "redirect:/home";
    }

}
