package com.example.hotelservice.repository;

import com.example.hotelservice.domain.Role;
import com.example.hotelservice.domain.person.Person;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonBaseRepository<T extends Person> extends JpaRepository<T, UUID> {
    T findByLogin(String username);

    List<T> findAllByRole(Role role);
}
