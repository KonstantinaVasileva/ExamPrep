package com.philately.controller;

import com.philately.model.dto.StampDTO;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.model.enums.PaperType;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import com.philately.service.CurrentUser;
import com.philately.service.StampService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stamps")
public class StampController {



    private final StampService stampService;
    private final CurrentUser currentUser;

    public StampController(StampService stampService, CurrentUser currentUser) {
        this.stampService = stampService;
        this.currentUser = currentUser;
    }


    @GetMapping("/add")
    public String addStamp() {

        if (!currentUser.isLoggedIn()){
            return "redirect:/";
        }

        return "add-stamp";
    }

    @ModelAttribute("stampDTO")
    public StampDTO stampDTO() {
        return new StampDTO();
    }

    @ModelAttribute("papers")
    public PaperType[] getAllPapers() {
        return PaperType.values();
    }

    @PostMapping("/add")
    public String addStamp(@Valid StampDTO stampDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("stampDTO", stampDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.stampDTO", bindingResult);
            return "redirect:/stamps/add";
        }
        stampService.addStamp(stampDTO);
        return "redirect:/";
    }

    @GetMapping("/add-to-wish/{id}")
    public String addToWishlist(@PathVariable long id){
        stampService.addToWishlist(id, currentUser.getId());
        return "redirect:/";
    }

    @GetMapping("/remove-from-wish/{id}")
    public String removeFromWishlist(@PathVariable long id){
        stampService.removeFromWishlist(id, currentUser.getId());
        return "redirect:/";
    }

    @GetMapping("/buy")
    public String buyAll(){
        stampService.buyAllStamps(currentUser.getId());
        return "redirect:/";
    }

}
