package com.plannerapp.controller;

import com.plannerapp.model.dto.LoginUserDTO;
import com.plannerapp.model.dto.RegisterUserDTO;
import com.plannerapp.service.CurrentUser;
import com.plannerapp.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final CurrentUser currentUser;
    private final UserServiceImpl userServiceImpl;

    public UserController(CurrentUser currentUser, UserServiceImpl userServiceImpl) {
        this.currentUser = currentUser;
        this.userServiceImpl = userServiceImpl;
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }

    @ModelAttribute("loginUserDTO")
    public LoginUserDTO loginUserDTO() {
        return new LoginUserDTO();
    }

    @ModelAttribute("login")
    public void addAttributes(Model model) {
        model.addAttribute("login");
    }

    @GetMapping("/login")
    public String login() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
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
        boolean register = userServiceImpl.register(registerUserDTO);
        if (bindingResult.hasErrors() || !register) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUserDTO", bindingResult);
            return "redirect:/users/register";
        }

        userServiceImpl.registration(registerUserDTO);

        return "redirect:/users/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginUserDTO loginUserDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginUserDTO", bindingResult);
            return "redirect:/users/login";
        }
        boolean login = userServiceImpl.login(loginUserDTO);
        if (!login) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute("login", false);
            return "redirect:/users/login";
        }

        currentUser.login(loginUserDTO.getUsername());
        currentUser.setId(userServiceImpl.getUserId(loginUserDTO));
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        currentUser.logout();
        return "redirect:/";
    }

}
