package com.example.hotelservice.dto;

import com.example.hotelservice.domain.Role;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    @NotNull
    private UUID personId;

    @NotNull
    private String imageUrl;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Role role;

    private int room;
}
