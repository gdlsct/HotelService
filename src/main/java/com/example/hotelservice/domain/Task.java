package com.example.hotelservice.domain;

import com.example.hotelservice.domain.person.Guest;
import com.example.hotelservice.domain.person.Worker;
import com.example.hotelservice.dto.TaskDTO;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @NotNull
    @NotEmpty
    private String description;

    @OneToOne
    @JoinColumn(name = "status_id")
    @NotNull
    private Status status;

    @OneToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @Min(value = 0)
    @Max(value = 5)
    private int rating;

    private String dispatcherComment;

    private String inventory;
}
