package com.example.hotelservice.repository;

import com.example.hotelservice.domain.person.Guest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GuestRepository extends PersonBaseRepository<Guest>{
}
