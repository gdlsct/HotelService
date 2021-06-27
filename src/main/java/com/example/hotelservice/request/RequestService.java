package com.example.hotelservice.request;

import com.example.hotelservice.authentication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserService userService;


    public List<Request> getAllRequest() {
        return requestRepository.findAllByOrderByIdDesc();
    }

    public void newRequest(Request request, Principal principal) {

        request.setLogin(userService.findUserByUserName(principal.getName()).getUserName());
        request.setFirstName(userService.findUserByUserName(principal.getName()).getName());
        request.setLastName(userService.findUserByUserName(principal.getName()).getLastName());
        request.setRoom(userService.findUserByUserName(principal.getName()).getRoom());
        request.setStatus(Status.CREATED);
        request.setWorker("Не назначен");
        request.setRating("Нет оценки");
        request.setDispatcher_comment("");
        this.requestRepository.save(request);
    }

    public void updateRequest(Request request) {
        this.requestRepository.save(request);
    }

    public Request getRequestById(long id) {
        Optional<Request> optional = requestRepository.findById(id);
        Request request;
        if (optional.isPresent()) {
            request = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return request;
    }


    public List<Request> getRequestByWorker(String worker) {
        List<Request> requests = requestRepository.findAllByWorkerOrderByIdDesc(worker);

        if (requests == null) {
            throw new RuntimeException(" Employee not found for worker :: " + worker);
        }

        return requests;
    }

    public List<Request> getRequestByGuest(String guest) {
        List<Request> requests = requestRepository.findAllByLoginOrderByIdDesc(guest);

        if (requests == null) {
            throw new RuntimeException(" Employee not found for guest :: " + guest);
        }

        return requests;
    }
}
