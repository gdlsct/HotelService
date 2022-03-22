package com.example.hotelservice.repository;

import com.example.hotelservice.domain.person.Worker;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface WorkerRepository extends PersonBaseRepository<Worker> {

}
