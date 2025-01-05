package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.service.CurrentUser;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddWordController {

    private final CurrentUser currentUser;
    private final WordService wordService;

    public AddWordController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping("/words/add")
    public String addWord() {
        if (!currentUser.isLoggedIn()){
            return "redirect:/user/login";
        }
        return "word-add";
    }

    @ModelAttribute("wordDTO")
    public WordDTO wordDTO() {
        return new WordDTO();
    }

    @PostMapping("/words/add")
    public String addWord(@Valid WordDTO wordDTO,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordDTO", wordDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.wordDTO", result);
            return "redirect:/words/add";
        }

        wordService.addWord(wordDTO);

        return "redirect:/home";
    }
}
