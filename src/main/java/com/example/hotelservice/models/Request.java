package com.example.hotelservice.models;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login")
    @NotNull
    private String login;

    @Column(name = "firstName")
    @NotNull
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    private String lastName;

    @Column(name = "room")
    @NotNull
    private int room;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "status")
    @NotNull
    private Status status;

    @Column(name = "worker")
    private String worker;

    @Column(name = "rating")
    private String rating;

    @Column(name = "dispatcher_comment")
    private String dispatcher_comment;
}
