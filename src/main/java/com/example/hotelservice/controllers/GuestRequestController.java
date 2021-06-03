package com.example.hotelservice.controllers;

import com.example.hotelservice.models.Request;
import com.example.hotelservice.repositories.UserRepository;
import com.example.hotelservice.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/guest/requests")
public class GuestRequestController {

    @Autowired
    private RequestService requestService;

    private final UserRepository userRepository;

    @Autowired
    public GuestRequestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String showRequestsList(Model model) {
        model.addAttribute("requests", requestService.getAllRequest());
        return "guest/requests";
    }

    @GetMapping(value = {"/new"})
    public String showNewRequestForm(Model model) {

        Request request = new Request();

        model.addAttribute("request", request);

        return "guest/new";
    }

    @PostMapping(value = "")
    public String newRequest(@ModelAttribute("request") Request request, Principal principal) {

        requestService.newRequest(request, principal);

        return "redirect:/guest/requests";
    }

    @GetMapping("/{id}")
    public String showUpdateRequestForm(@PathVariable("id") long id, Model model) {
        Request request = requestService.getRequestById(id);

        model.addAttribute("request", request);
        return "guest/edit";
    }

    @PostMapping(value = "/{id}")
    public String updateRequest(@PathVariable("id") long id, @ModelAttribute("request") Request newRequest) {

        Request request = requestService.getRequestById(id);
        request.setDescription(newRequest.getDescription());

        requestService.updateRequest(request);

        return "redirect:/guest/requests";
    }

    @GetMapping("/{id}/cancel")
    public String cancelRequest(@PathVariable("id") long id) {

        this.requestService.cancelRequestById(id);

        return "redirect:/guest/requests";
    }

}
