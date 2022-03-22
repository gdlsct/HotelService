package com.example.hotelservice.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class NewPersonDTO {

    @NotNull
    @Length(min = 5, message = "*Your user name must have at least 5 characters")
    private String login;

    @NotNull
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String password;

    @NotNull
    @Length(min = 1, message = "*Your first name must have at least 1 characters")
    private String firstName;

    @NotNull
    @Length(min = 1, message = "*Your last name must have at least 1 characters")
    private String lastName;

    @NotNull
    private String role;

    private int room;
}
