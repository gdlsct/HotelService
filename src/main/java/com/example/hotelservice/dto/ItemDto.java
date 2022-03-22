package com.example.hotelservice.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    @NotNull
    private UUID newsId;

    @NotNull
    private String name;

    private String imgUrl;

    @NotNull
    private String description;
}
