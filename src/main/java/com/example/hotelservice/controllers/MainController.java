package com.example.hotelservice.controllers;

import java.security.Principal;

import com.example.hotelservice.models.AppUser;
import com.example.hotelservice.repositories.RequestRepository;
import com.example.hotelservice.repositories.UserRepository;
import com.example.hotelservice.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "admin";
    }

    @GetMapping(value = "/info")
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();


        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @GetMapping(value = {"/"})
    public String index() {

        return "login";
    }

    @GetMapping(value = {"/news"})
    public String news() {
        return "news";
    }

    @GetMapping("/guest")
    public String guest() {
        return "guest/requests";
    }

    @GetMapping("/dispatcher")
    public String dispatcher() {
        return "dispatcher/requests";
    }

    @GetMapping("/worker")
    public String worker() {
        return "worker/requests";
    }
}
