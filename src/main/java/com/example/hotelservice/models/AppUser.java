package com.example.hotelservice.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "App_User", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "User_Id", nullable = false)
    private Long userId;

    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;

    @Column(name = "User_FirstName", length = 36, nullable = false)
    private String userFirstName;

    @Column(name = "User_LastName", length = 36, nullable = false)
    private String userLastName;

    @Column(name = "User_Room", nullable = false)
    private int userRoom = 0;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;
}
