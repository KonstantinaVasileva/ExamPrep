package com.resellerapp.controller;

import com.resellerapp.model.dto.NewOffer;
import com.resellerapp.model.entity.User;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final UserService userService;

    public OfferController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView add(HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("offer-add");
        mav.addObject("user", user);
        mav.addObject("newOffer", new NewOffer());
        return mav;
    }

    @PostMapping("/add")
    public String addOffer(@Valid NewOffer newOffer, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "offer-add";
        }
        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        offerService.addOffer(newOffer, user);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeOffer(@PathVariable UUID id) {
        offerService.removeOffers(id);
        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    public String buyOffer(@PathVariable UUID id, HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        offerService.buyOffers(id, user);
        return "redirect:/home";
    }
}
