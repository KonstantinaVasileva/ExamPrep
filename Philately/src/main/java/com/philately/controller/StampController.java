package com.philately.controller;

import com.philately.model.dto.StampDTO;
import com.philately.model.entity.Stamp;
import com.philately.model.enums.PaperType;
import com.philately.service.StampService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        return "redirect:/home";
    }

}
