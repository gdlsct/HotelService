package com.example.hotelservice;

import java.security.Principal;
import java.util.List;

import com.example.hotelservice.authentication.role.Role;
import com.example.hotelservice.authentication.role.RoleRepository;
import com.example.hotelservice.authentication.user.User;
import com.example.hotelservice.authentication.user.UserService;
import com.example.hotelservice.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;

    @Autowired
    public MainController(UserService userService, UserDetailsService userDetailsService, RoleRepository roleRepository) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
    }

    @GetMapping(value = "/profile")
    public String userInfo(Model model, Principal principal) {

        org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

        String firstName = userService.findUserByUserName(principal.getName()).getName();
        String lastName = userService.findUserByUserName(principal.getName()).getLastName();

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("userInfo", loginedUser.getAuthorities());

        return "common/profile";
    }

    @GetMapping(value = {"/login"})
    public String index() {

        return "common/login";
    }

    @GetMapping(value = {"/"})
    public String news() {
        return "common/news";
    }

    @GetMapping(value = {"/users"})
    public String users(Model model) {

        model.addAttribute("users", userService.findAllUsers());

        return "admin/list";
    }

    @GetMapping(value={"/registration"})
    public String showRegistrationForm(Model model){

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        System.out.println(roleRepository.findAll());

        return "admin/registration";
    }

    @PostMapping(value={"/registration"})
    public String addUser(@ModelAttribute("user") User user){

        userService.saveUser(user);

        return "redirect:/users";
    }

}