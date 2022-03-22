package com.example.hotelservice.service;

import com.example.hotelservice.dto.TaskDTO;
import java.security.Principal;
import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTasks(Principal principal);

    void createTask(final TaskDTO taskDTO, final Principal principal);

    void updateTask(Long id, TaskDTO dto);

    void cancelTask(Long id, Principal principal);

    void successTask(Long id, TaskDTO dto);

    void rateTask(Long id, TaskDTO dto);

    TaskDTO getTaskDTOById(Long id, Principal principal);
}
