package com.example.hotelservice.mapper;

import com.example.hotelservice.domain.News;
import com.example.hotelservice.dto.ItemDto;
import org.mapstruct.Mapper;

@Mapper
public interface NewsMapper {

    ItemDto mapEntityToDto(News item);
}
