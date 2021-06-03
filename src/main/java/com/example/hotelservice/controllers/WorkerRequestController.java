package com.example.hotelservice.controllers;

import com.example.hotelservice.models.Request;
import com.example.hotelservice.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class WorkerRequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("")
    public String showRequestsList(Model model) {
        model.addAttribute("requests", requestService.getAllRequest());
        return "worker/requests";
    }

    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Request request = requestService.getRequestById(id);

        model.addAttribute("request", request);
        return "worker/edit";
    }

    @GetMapping("/{id}/cancel")
    public String cancelRequest(@PathVariable("id") long id, Model model) {

        this.requestService.cancelRequestById(id);

        return "redirect:/worker/requests";
    }
}
