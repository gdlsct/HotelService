package com.example.hotelservice.controller;

import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.repository.PersonBaseRepository;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CommonController {

    private final PersonBaseRepository<Person> personBaseRepository;

    @GetMapping(value = "/profile")
    public String userInfo(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));
        model.addAttribute("userInfo", loginedUser.getAuthorities());

        return "common/profile";
    }

    @GetMapping(value = {"/login"})
    public String login() {
        return "common/login";
    }

    @GetMapping(value = {"/logout"})
    public String logout() {
        return "redirect:/common/login";
    }

    @GetMapping
    public String news() {
        return "redirect:/news";
    }
}