package com.example.hotelservice.service.impl;

import com.example.hotelservice.domain.News;
import com.example.hotelservice.dto.ItemDto;
import com.example.hotelservice.repository.NewsRepository;
import com.example.hotelservice.service.NewsService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public void createItem(final ItemDto dto) {
        News item = new News();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setDate(LocalDateTime.now());
        if (dto.getImgUrl() == null || dto.getImgUrl().isEmpty()) {
            item.setImgUrl("https://tacm.com/wp-content/uploads/2018/01/no-image-available.jpeg");
        }
        newsRepository.save(item);
    }

    @Override
    public void deleteItem(UUID uuid) {
        newsRepository.deleteById(uuid);
    }

    @Override
    public void updateItem(UUID id, ItemDto dto) {
        News item = newsRepository.findByNewsId(id);
        item.setImgUrl(dto.getImgUrl());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setDate(LocalDateTime.now());
        newsRepository.save(item);
    }
}
