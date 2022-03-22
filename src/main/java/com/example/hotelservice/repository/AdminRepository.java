package com.example.hotelservice.repository;

import com.example.hotelservice.domain.person.Admin;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminRepository extends PersonBaseRepository<Admin> {
}
