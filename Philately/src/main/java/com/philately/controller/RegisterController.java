package com.philately.controller;

import com.philately.model.dto.UserRegistrationDTO;
import com.philately.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        boolean registration = userService.userRegistration(userRegistrationDTO);
        if (bindingResult.hasErrors() || !registration) {
            redirectAttributes.addFlashAttribute(
                    "userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/users/register";
        }

//        if (userRegistrationDTO.isPasswordsEqual()) {
//            userService.userRegistration(userRegistrationDTO);
//        } else {
//            model.addAttribute("error", "Passwords do not match");
//            return "register";
//        }
        return "redirect:/users/login";
    }

}
