package com.example.hotelservice.repository;

import com.example.hotelservice.domain.Task;
import com.example.hotelservice.domain.person.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByTaskIdDesc();

    List<Task> findAllByWorkerLoginOrderByTaskIdDesc(String worker);

    List<Task> findAllByGuestLoginOrderByTaskIdDesc(String login);

}