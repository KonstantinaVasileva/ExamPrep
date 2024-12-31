package com.philately.controller;

import com.philately.model.dto.LoginUserDTO;
import com.philately.model.entity.User;
import com.philately.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loginUserDTO")
    public LoginUserDTO loginUserDTO() {
        return new LoginUserDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginUserDTO loginUserDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        User user = userService.findByUsername(loginUserDTO.getUsername());
        if (user == null || !user.getPassword().equals(loginUserDTO.getPassword())) {
            bindingResult.addError(
                    new FieldError("incorrect-login", "username", "Incorrect username or password")
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginUserDTO", bindingResult
            );
            return "redirect:/users/login";
        }
        boolean login = userService.userLogin(loginUserDTO);
        if (!login) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginUserDTO", bindingResult
            );
            return "redirect:/users/login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.userLogout();
        return "redirect:/";
    }

}
