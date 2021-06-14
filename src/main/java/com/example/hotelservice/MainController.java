package com.example.hotelservice;

import java.security.Principal;
import java.util.List;

import com.example.hotelservice.authentication.users.AppUser;
import com.example.hotelservice.authentication.users.UserRepository;
import com.example.hotelservice.authentication.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Autowired
    public MainController(UserRepository userRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/profile")
    public String userInfo(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String firstName = userRepository.findUserAccount(principal.getName()).getUserFirstName();
        String lastName = userRepository.findUserAccount(principal.getName()).getUserLastName();

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("userInfo", loginedUser.getAuthorities());

        return "profile";
    }

    @GetMapping(value = {"/login"})
    public String index() {

        return "login";
    }

    @GetMapping(value = {"/", "/news"})
    public String news() {
        return "news";
    }

    @GetMapping(value = {"/users"})
    public String users(Model model) {


        model.addAttribute("users", userRepository.findAllUsers());
        List<UserDetails> userDetailsList = null;


        for (AppUser appUser : userRepository.findAllUsers()) {
            System.out.println(userDetailsService.loadUserByUsername(appUser.getUserName()));
        }

//        model.addAttribute("users", userDetailsList);

        return "admin/list";
    }

}