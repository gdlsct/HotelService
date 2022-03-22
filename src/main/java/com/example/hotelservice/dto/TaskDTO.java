package com.example.hotelservice.dto;

import com.example.hotelservice.domain.Status;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    @NotNull
    private Long taskId;

    private String guestFirstName;

    private String guestLastName;

    private int guestRoom;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private Status status;

    private String workerFirstName;

    private String workerLastName;

    private String workerLogin;

    @Min(value = 0)
    @Max(value = 5)
    private int rating;

    private String dispatcherComment;

    private String inventory;

    public static TaskDTO getNewDTO(PersonDTO personDTO){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setGuestFirstName(personDTO.getFirstName());
        taskDTO.setGuestLastName(personDTO.getLastName());
        return taskDTO;
    }
}
