package com.example.hotelservice.authentication.user;

import com.example.hotelservice.authentication.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = {""})
    public String users(Model model) {

        model.addAttribute("users", userService.findAllUsers());

        return "admin/list";
    }

    @GetMapping(value={"/new"})
    public String showRegistrationForm(Model model){

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        System.out.println(roleRepository.findAll());

        return "admin/registration";
    }

    @PostMapping(value={""})
    public String addUser(@ModelAttribute("user") User user){

        userService.saveUser(user);

        return "redirect:/users";
    }

}
