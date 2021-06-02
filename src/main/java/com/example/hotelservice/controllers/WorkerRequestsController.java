//package com.example.hotelservice.controllers;
//
//import com.example.hotelservice.models.Request;
//import com.example.hotelservice.models.Status;
//import com.example.hotelservice.repositories.RequestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//public class WorkerRequestsController {
//
//
//    private final RequestRepository requestRepository;
//
//    @Autowired
//    public WorkerRequestsController(RequestRepository requestRepository) {
//        this.requestRepository = requestRepository;
//    }
//
//    @GetMapping("")
//    public String showRequestsList(Model model) {
//        model.addAttribute("requests", requestRepository.findAllByOrderByIdDesc());
//        return "guest/requests";
//    }
//
//    @GetMapping(value = {"/new"})
//    public String showNewRequest(Model model) {
//        Request request = new Request();
//        model.addAttribute("request", request);
//
//        return "guest/new";
//    }
//
//    @PostMapping(value = "")
//    public String addRequest(@ModelAttribute("request") Request request,
//                             BindingResult bindingResult,
//                             Model model) {
//        requestRepository.save(request);
//        model.addAttribute("request", "request_attributes");
//        return "redirect:/guest/requests";
//    }
//
//    @GetMapping("/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        Request request = requestRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
//        model.addAttribute("request", request);
//        return "guest/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String updateRequest(@PathVariable("id") long id, @Valid Request request, BindingResult result,
//                                Model model) {
//        if (result.hasErrors()) {
//            request.setId(id);
//            return "guest/edit";
//        }
//
//        requestRepository.save(request);
//        model.addAttribute("request", requestRepository.findAll());
//        return "redirect:/guest/requests";
//    }
//
//    @PatchMapping("/{id}/cancel")
//    public String deleteRequest(@PathVariable("id") long id, Model model) {
//        Request request = requestRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
//        request.setStatus(Status.CANCELLED_BY_GUEST);
//        requestRepository.save(request);
//        model.addAttribute("request", requestRepository.findAll());
//        return "redirect:/guest/requests";
//    }
//}
