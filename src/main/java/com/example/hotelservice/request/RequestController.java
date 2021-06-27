package com.example.hotelservice.request;

import com.example.hotelservice.authentication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showRequestsList(Model model, Principal principal) {
        model.addAttribute("requests", requestService.getAllRequest());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        if (loginedUser.getAuthorities().toString().contains("ROLE_GUEST")){

            model.addAttribute("requests", requestService.getRequestByGuest(loginedUser.getUsername()));

            return "guest/requests";
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_DISPATCHER")){

            return "dispatcher/requests";
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_ADMIN")){

            return "worker/requests";
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_WORKER")){

            model.addAttribute("requests", requestService.getRequestByWorker(userService.findUserByUserName(loginedUser.getUsername()).getLastName()));

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

            model.addAttribute("users", userService.findUsersByRole("ROLE_WORKER"));

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
    public String cancelRequest(@PathVariable("id") long id, Principal principal) {

        Request request = requestService.getRequestById(id);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();


        if (loginedUser.getAuthorities().toString().contains("ROLE_GUEST")){
            request.setStatus(Status.CANCELLED_BY_GUEST);
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_DISPATCHER")){
            request.setStatus(Status.CANCELLED_BY_DISPATCHER);
        }

        if (loginedUser.getAuthorities().toString().contains("ROLE_WORKER")){
            request.setStatus(Status.CANCELLED_BY_WORKER);
            request.setInventory("Не использовались");
        }

        requestService.updateRequest(request);

        return "redirect:/requests";
    }

    @PostMapping("/{id}/success")
    public String successRequest(@PathVariable("id") long id, @ModelAttribute("request") Request newRequest) {

        Request request = requestService.getRequestById(id);

        request.setStatus(Status.DONE);
        request.setInventory(newRequest.getInventory());

        requestService.updateRequest(request);

        return "redirect:/requests";
    }
}
