package com.example.hotelservice.requests;

import com.example.hotelservice.authentication.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    private final UserRepository userRepository;

    @Autowired
    public RequestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String showRequestsList(Model model, Principal principal) {
        model.addAttribute("requests", requestService.getAllRequest());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        if (loginedUser.getAuthorities().toString().contains("ROLE_GUEST")){
            return "guest/requests";
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_DISPATCHER")){
            return "dispatcher/requests";
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_WORKER")){
            return "worker/requests";
        }

        return "";
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

        return "redirect:/requests";
    }

    @GetMapping("/{id}")
    public String showUpdateRequestForm(@PathVariable("id") long id, Model model, Principal principal) {
        Request request = requestService.getRequestById(id);

        model.addAttribute("request", request);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        if (loginedUser.getAuthorities().toString().contains("ROLE_GUEST")){
            return "guest/edit";
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_DISPATCHER")){
            return "dispatcher/edit";
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_WORKER")){
            return "worker/edit";
        }

        return "";
    }

    @PostMapping(value = "/{id}")
    public String updateRequest(@PathVariable("id") long id, @ModelAttribute("request") Request newRequest) {

        Request request = requestService.getRequestById(id);

        if (newRequest.getDescription() != null) {
            request.setDescription(newRequest.getDescription());
        }

        if (newRequest.getWorker() != null) {
            request.setWorker(newRequest.getWorker());
            request.setStatus(Status.ASSIGNED);
        }

        if (newRequest.getDispatcher_comment() != null) {
            request.setDispatcher_comment(newRequest.getDispatcher_comment());
        }

        requestService.updateRequest(request);

        return "redirect:/requests";
    }

    @GetMapping("/{id}/cancel")
    public String cancelRequest(@PathVariable("id") long id) {

        this.requestService.cancelRequestById(id);

        return "redirect:/requests";
    }

}
