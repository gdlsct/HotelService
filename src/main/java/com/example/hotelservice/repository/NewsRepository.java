package com.example.hotelservice.repository;

import com.example.hotelservice.domain.News;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {

    List<News> findAllByOrderByDateDesc();

    News findByNewsId(UUID uuid);
}
