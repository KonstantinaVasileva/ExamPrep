package com.philately.controller;

import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.service.CurrentUser;
import com.philately.service.StampService;
import com.philately.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final StampService stampService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, StampService stampService, UserService userService) {
        this.currentUser = currentUser;
        this.stampService = stampService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {

        if (currentUser.isLoggedIn()) {
            User user = userService.findById(currentUser.getId());

            Set<Stamp> myStamps = stampService.getAddedStamp(user);
            model.addAttribute("myStamps", myStamps);

            List<Stamp> offeredStamps = stampService.getOthersStamp(user);
            model.addAttribute("offeredStamps", offeredStamps);

            Long id = user.getId();
            Set<Stamp> myPurchases = userService.getPurchasesStamp(id);
            model.addAttribute("myPurchases", myPurchases);

            Set<Stamp> myWishlist = userService.getMyWishlist(id);
            model.addAttribute("myWishlist", myWishlist);

            return "home";
        }

        return "index";
    }

//    @GetMapping("/home")
//    public String homeLogin() {
//
//        return "home";
//    }

}
