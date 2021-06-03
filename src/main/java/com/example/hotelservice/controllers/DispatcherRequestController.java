package com.example.hotelservice.controllers;

import com.example.hotelservice.models.Request;
import com.example.hotelservice.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/dispatcher/requests")
public class DispatcherRequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("")
    public String showRequestsList(Model model) {
        model.addAttribute("requests", requestService.getAllRequest());
        return "dispatcher/requests";
    }

    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Request request = requestService.getRequestById(id);

        model.addAttribute("request", request);
        return "dispatcher/edit";
    }

    @GetMapping("/{id}/cancel")
    public String cancelRequest(@PathVariable("id") long id, Model model) {

        this.requestService.cancelRequestById(id);

        return "redirect:/guest/requests";
    }
}
