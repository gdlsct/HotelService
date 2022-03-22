package com.example.hotelservice.repository;

import com.example.hotelservice.domain.Status;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, UUID> {

    Status findByStatusName(String statusName);
}
