package com.example.hotelservice.service;

import com.example.hotelservice.domain.Task;
import com.example.hotelservice.dto.TaskDTO;
import java.security.Principal;
import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTasks(Principal principal);

    List<Task> getTasks();

    void createTask(TaskDTO taskDTO, Principal principal);

    Task newTask(TaskDTO taskDTO);

    void updateTask(Long id, TaskDTO dto);

    Task updateTaskById(Long id, TaskDTO dto);

    void cancelTask(Long id, Principal principal);

    void successTask(Long id, TaskDTO dto);

    void rateTask(Long id, TaskDTO dto);

    TaskDTO getTaskDTOById(Long id, Principal principal);

    Task getTaskById(Long id);
}
