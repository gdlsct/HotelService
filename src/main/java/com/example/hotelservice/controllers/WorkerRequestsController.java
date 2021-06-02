package com.example.hotelservice.controllers;

import com.example.hotelservice.models.Request;
import com.example.hotelservice.models.Status;
import com.example.hotelservice.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class WorkerRequestsController {

    private final RequestRepository requestRepository;

    @Autowired
    public WorkerRequestsController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping("")
    public String showRequestsList(Model model) {
        model.addAttribute("requests", requestRepository.findAllByOrderByIdDesc());
        return "worker/requests";
    }

    @PostMapping(value = "")
    public String addRequest(@ModelAttribute("request") Request request,
                             BindingResult bindingResult,
                             Model model) {
        requestRepository.save(request);
        model.addAttribute("request", "request_attributes");
        return "redirect:/worker/requests";
    }

    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        model.addAttribute("request", request);
        return "worker/edit";
    }

    @PatchMapping("/{id}")
    public String updateRequest(@PathVariable("id") long id, @Valid Request request, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            request.setId(id);
            return "worker/edit";
        }

        requestRepository.save(request);
        model.addAttribute("request", requestRepository.findAll());
        return "redirect:/worker/requests";
    }

    @PatchMapping("/{id}/end")
    public String deleteRequest(@PathVariable("id") long id, Model model) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        request.setStatus(Status.DONE);
        requestRepository.save(request);
        model.addAttribute("request", requestRepository.findAll());
        return "redirect:/worker/requests";
    }
}
