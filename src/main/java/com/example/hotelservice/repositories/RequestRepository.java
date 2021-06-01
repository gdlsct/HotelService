package com.example.hotelservice.repositories;

import com.example.hotelservice.models.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findAllByOrderByIdDesc();
}
