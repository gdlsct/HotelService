package com.example.hotelservice.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "status")
    private Status status = Status.CREATED;

    @Column(name = "dispatcher_comment")
    private String dispatcher_comment = "";

    @Column(name = "rating")
    private String rating = "Нет оценки";

    @Column(name = "room")
    private int room;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "worker")
    private String worker = "Не назначен";
}
