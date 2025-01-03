package com.paintingscollectors.controller;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.LoggedInUser;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final LoggedInUser loggedInUser;
    private final PaintingService paintingService;
    private final UserService userService;

    public HomeController(LoggedInUser loggedInUser, PaintingService paintingService, UserService userService) {
        this.loggedInUser = loggedInUser;
        this.paintingService = paintingService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String homeIndex() {
        if (loggedInUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedInUser.isLoggedIn()) {
            return "redirect:/";
        }

        String username = loggedInUser.getUsername();
        User user = userService.findUserByUsername(username);
        List<Painting> myPainting = paintingService.getMyPainting(user);
        model.addAttribute("myPainting", myPainting);

        List<Painting> otherPainting = paintingService.getOtherUserPainting(user);
        model.addAttribute("otherPainting", otherPainting);

        Set<Painting> myFavorite = userService.myFavoritePainting(username);
        model.addAttribute("myFavorite", myFavorite);

        List<Painting> mostRatedPainting = paintingService.getMostRatedPainting();
        model.addAttribute("mostRatedPainting", mostRatedPainting);

        return "home";
    }

}
