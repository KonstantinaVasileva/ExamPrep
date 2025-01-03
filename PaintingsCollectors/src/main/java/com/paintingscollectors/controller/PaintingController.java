package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.entity.enums.StyleType;
import com.paintingscollectors.service.LoggedInUser;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/painting")
public class PaintingController {

    private final LoggedInUser loggedInUser;
    private final PaintingService paintingService;

    public PaintingController(LoggedInUser loggedInUser, PaintingService paintingService) {
        this.loggedInUser = loggedInUser;
        this.paintingService = paintingService;
    }

    @ModelAttribute("paintingDTO")
    public PaintingDTO paintingDTO() {
        return new PaintingDTO();
    }

    @ModelAttribute("styles")
    public StyleType[] styles() {
        return StyleType.values();
    }

    @GetMapping("/add")
    public String add() {

        if (!loggedInUser.isLoggedIn()) {
            return "redirect:/login";
        }
        return "add-painting";
    }

    @PostMapping("/add")
    public String add(@Valid PaintingDTO paintingDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("paintingDTO", paintingDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.paintingDTO", bindingResult);

            return "redirect:/painting/add";
        }

        paintingService.addPainting(paintingDTO);

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id) {
        if (!loggedInUser.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.removePainting(id);
        return "redirect:/home";
    }

    @GetMapping("/favorite/{id}")
    public String favorite(@PathVariable long id) {
        if (!loggedInUser.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.addToFavorite(id);
        return "redirect:/home";
    }

    @GetMapping("/remove-favorite/{id}")
    public String removeFavorite(@PathVariable long id) {
        if (!loggedInUser.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.removeFromFavorite(id);
        return "redirect:/home";
    }

    @GetMapping("/vote/{id}")
    public String vote(@PathVariable long id) {
        if (!loggedInUser.isLoggedIn()) {
            return "redirect:/login";
        }
        paintingService.votePainting(id);
        return "redirect:/home";
    }

}
