package com.example.hotelservice.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByOrderByIdDesc();

    List<Request> findAllByWorkerOrderByIdDesc(String worker);

    List<Request> findAllByLoginOrderByIdDesc(String login);

}

