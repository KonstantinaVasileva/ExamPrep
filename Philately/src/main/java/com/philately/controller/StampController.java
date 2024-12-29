package com.philately.controller;

import com.philately.model.dto.StampDTO;
import com.philately.model.entity.Stamp;
import com.philately.service.StampService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stamps")
public class StampController {

    private final StampService stampService;

    public StampController(StampService stampService) {
        this.stampService = stampService;
    }


    @GetMapping("/add")
    public String addStamp() {
        return "add-stamp";
    }

    @ModelAttribute("stampDTO")
    public StampDTO stampDTO() {
        return new StampDTO();
    }

    @PostMapping("/add")
    public String addStamp(StampDTO stampDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("stampDTO", stampDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.stampDTO", new StampDTO());
            return "redirect:/stamps/add";
        }
        stampService.addStamp(stampDTO);
        return "redirect:/home";
    }

}
