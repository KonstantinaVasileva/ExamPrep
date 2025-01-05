package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageType;
import com.dictionaryapp.service.CurrentUser;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final WordService wordService;

    public HomeController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String home() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String usersHome(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        List<Word> german = wordService.getGermanyWord(LanguageType.GERMAN);
        List<Word> spanish = wordService.getGermanyWord(LanguageType.SPANISH);
        List<Word> french = wordService.getGermanyWord(LanguageType.FRENCH);
        List<Word> italian = wordService.getGermanyWord(LanguageType.ITALIAN);

        model.addAttribute("german", german);
        model.addAttribute("spanish", spanish);
        model.addAttribute("french", french);
        model.addAttribute("italian", italian);

        int allWords = german.size() + spanish.size() + french.size() + italian.size();
        model.addAttribute("allWords", allWords);

        return "home";
    }

}
