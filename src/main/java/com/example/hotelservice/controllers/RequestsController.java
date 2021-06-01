package com.example.hotelservice.controllers;

import com.example.hotelservice.models.Request;
import com.example.hotelservice.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/requests/")
public class RequestsController {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestsController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping("list")
    public String showRequestsList(Model model) {
        model.addAttribute("requests", requestRepository.findAllByOrderByIdDesc());
        return "requests";
    }

    @GetMapping(value = {"new"})
    public String showNewRequest(Model model) {
        Request request = new Request();
        model.addAttribute("request", request);

        return "new";
    }

    @PostMapping(value = "new")
    public String addRequest(@ModelAttribute("request") Request request,
                             BindingResult bindingResult,
                             Model model) {
        requestRepository.save(request);
        model.addAttribute("mode", "MODE_TASKS");
        return "index";
    }
}
