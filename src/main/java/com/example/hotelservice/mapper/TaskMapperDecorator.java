package com.example.hotelservice.mapper;

import com.example.hotelservice.domain.Task;
import com.example.hotelservice.dto.PersonDTO;
import com.example.hotelservice.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class TaskMapperDecorator implements TaskMapper {

    @Autowired
    @Qualifier("delegate")
    private TaskMapper delegate;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public TaskDTO mapEntityToGuestDTO(final Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setGuestFirstName(task.getGuest().getFirstName());
        dto.setGuestLastName(task.getGuest().getLastName());
        dto.setTaskId(task.getTaskId());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setRating(task.getRating());

        return dto;
    }

    @Override
    public TaskDTO mapEntityToWorkerDTO(final Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setTaskId(task.getTaskId());
        dto.setGuestRoom(task.getGuest().getRoom());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDispatcherComment(task.getDispatcherComment());
        dto.setInventory(task.getInventory());

        return dto;
    }

    @Override
    public TaskDTO mapEntityToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setTaskId(task.getTaskId());

        dto.setGuestFirstName(task.getGuest().getFirstName());
        dto.setGuestLastName(task.getGuest().getLastName());
        dto.setGuestRoom(task.getGuest().getRoom());


        if (task.getWorker() != null) {
            dto.setWorkerLogin(task.getWorker().getLogin());
            dto.setWorkerFirstName(task.getWorker().getFirstName());
            dto.setWorkerLastName(task.getWorker().getLastName());
        }

        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDispatcherComment(task.getDispatcherComment());
        dto.setInventory(task.getInventory());

        return dto;
    }
}
