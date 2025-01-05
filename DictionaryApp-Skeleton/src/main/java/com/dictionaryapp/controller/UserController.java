package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.service.CurrentUser;
import com.dictionaryapp.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
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

    @GetMapping("/register")
    public String register() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        if (currentUser.isLoggedIn()) {
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
        boolean register = userServiceImpl.register(registerUserDTO);
        if (bindingResult.hasErrors() || !register) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUserDTO", bindingResult);
            return "redirect:/user/register";
        }

        userServiceImpl.registration(registerUserDTO);

        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginUserDTO loginUserDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginUserDTO", bindingResult);
            return "redirect:/user/login";
        }
        boolean login = userServiceImpl.login(loginUserDTO);
        if (!login) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute("login", false);
            return "redirect:/user/login";
        }

        currentUser.login(loginUserDTO.getUsername());
        currentUser.setId(userServiceImpl.getUserId(loginUserDTO));
        return "redirect:/home";
    }

}
