package com.example.hotelservice.controllers;

import com.example.hotelservice.models.Request;
import com.example.hotelservice.models.Status;
import com.example.hotelservice.repositories.RequestRepository;
import com.example.hotelservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/guest/requests")
public class GuestRequestsController {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    @Autowired
    public GuestRequestsController(RequestRepository requestRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping("")
    public String showRequestsList(Model model) {
        model.addAttribute("requests", requestRepository.findAllByOrderByIdDesc());
        return "guest/requests";
    }

    @GetMapping(value = {"/new"})
    public String showNewRequest(Model model) {
        Request request = new Request();
        model.addAttribute("request", request);

        return "guest/new";
    }

    @PostMapping(value = "")
    public String addRequest(@ModelAttribute("request") Request request,
                             BindingResult bindingResult,
                             Model model, Principal principal) {
        request.setFirstName(userRepository.findUserAccount(principal.getName()).getUserFirstName());
        request.setLastName(userRepository.findUserAccount(principal.getName()).getUserLastName());
        request.setRoom(userRepository.findUserAccount(principal.getName()).getUserRoom());
        requestRepository.save(request);
        model.addAttribute("request", "request_attributes");
        return "redirect:/guest/requests";
    }

    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        model.addAttribute("request", request);
        return "guest/edit";
    }

    @PutMapping(value = "/{id}")
    public String putRequest(@PathVariable("id") long id, @ModelAttribute("request") Request request,
                             BindingResult bindingResult,
                             Model model, Principal principal) {
        request.setFirstName(userRepository.findUserAccount(principal.getName()).getUserFirstName());
        request.setLastName(userRepository.findUserAccount(principal.getName()).getUserLastName());
        request.setRoom(userRepository.findUserAccount(principal.getName()).getUserRoom());
        requestRepository.save(request);
        model.addAttribute("request", "request_attributes");
        return "redirect:/guest/requests";
    }

//    @PutMapping("/{id}")
//    public String updateRequest(@PathVariable("id") long id, @Valid Request request, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            request.setId(id);
//            return "guest/edit";
//        }
//        request = requestRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
//
////        requestRepository.save(request);
//        model.addAttribute("request", request);
//        requestRepository.save(request);
//        return "redirect:/guest/requests";
//    }

    @PatchMapping("/{id}/cancel")
    public String deleteRequest(@PathVariable("id") long id, Model model) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));

        request.setStatus(Status.CANCELLED_BY_GUEST);
        requestRepository.save(request);
        model.addAttribute("request", requestRepository.findAll());
        return "redirect:/guest/requests";
    }

}
