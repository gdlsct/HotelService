package com.example.hotelservice.service;

import com.example.hotelservice.dto.ItemDto;
import java.util.UUID;

public interface NewsService {

    void createItem(ItemDto dto);

    void deleteItem(UUID uuid);

    void updateItem(UUID id, ItemDto dto);
}
