package com.example.hotelservice.mapper;

import com.example.hotelservice.domain.Task;
import com.example.hotelservice.dto.TaskDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(TaskMapperDecorator.class)
public interface TaskMapper {
    TaskDTO mapEntityToGuestDTO(Task task);

    TaskDTO mapEntityToWorkerDTO(Task task);

    TaskDTO mapEntityToDTO(Task task);

    Task mapDtoToEntity(TaskDTO dto);
}
