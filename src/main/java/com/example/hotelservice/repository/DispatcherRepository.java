package com.example.hotelservice.repository;

import com.example.hotelservice.domain.person.Dispatcher;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DispatcherRepository extends PersonBaseRepository<Dispatcher> {
}
