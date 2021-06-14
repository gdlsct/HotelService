package com.example.hotelservice.requests;

import java.security.Principal;
import java.util.List;

public interface RequestService {

    List<Request> getAllRequest();

    void updateRequest(Request request);

    void newRequest(Request request, Principal principal);

    Request getRequestById(long id);

    void cancelRequestById(long id);

}
