package com.example.hotelservice.requests;

import com.example.hotelservice.authentication.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Request> getAllRequest() {
        return requestRepository.findAllByOrderByIdDesc();
    }

    @Override
    public void newRequest(Request request, Principal principal) {

        request.setFirstName(userRepository.findUserAccount(principal.getName()).getUserFirstName());
        request.setLastName(userRepository.findUserAccount(principal.getName()).getUserLastName());
        request.setRoom(userRepository.findUserAccount(principal.getName()).getUserRoom());
        request.setStatus(Status.CREATED);
        request.setWorker("Не назначен");
        request.setRating("Нет оценки");
        request.setDispatcher_comment("");
        this.requestRepository.save(request);
    }

    @Override
    public void updateRequest(Request request) {
        this.requestRepository.save(request);
    }

    @Override
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

    @Override
    public void cancelRequestById(long id) {
        this.requestRepository.findById(id).get().setStatus(Status.CANCELLED_BY_GUEST);

    }
}
