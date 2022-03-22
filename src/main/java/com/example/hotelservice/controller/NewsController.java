package com.example.hotelservice.controller;

import com.example.hotelservice.domain.News;
import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.dto.ItemDto;
import com.example.hotelservice.mapper.NewsMapper;
import com.example.hotelservice.repository.NewsRepository;
import com.example.hotelservice.repository.PersonBaseRepository;
import com.example.hotelservice.service.NewsService;
import java.security.Principal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;
    private final NewsService newsService;
    private final NewsMapper newsMapper;
    private final PersonBaseRepository<Person> personBaseRepository;

    @GetMapping
    public String getNewsList(Model model, Principal principal) {
        model.addAttribute("news", newsRepository.findAllByOrderByDateDesc());
        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));

        return "news/list";
    }

    @GetMapping(value = {"{id}"})
    public String getNewsView(@PathVariable("id") final UUID id, Model model, Principal principal) {
        model.addAttribute("item", newsRepository.findByNewsId(id));
        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));
        return "news/view";
    }

    @GetMapping(value = {"/new"})
    public String getCreateItemForm(Model model, Principal principal) {
        model.addAttribute("item", new ItemDto());
        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));
        return "news/new";
    }

    @GetMapping(value = {"{id}/edit"})
    public String getEditItemForm(@PathVariable("id") final UUID id, Model model, Principal principal) {
        model.addAttribute("item", newsMapper.mapEntityToDto(newsRepository.findByNewsId(id)));
        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));
        return "news/edit";
    }

    @PostMapping
    public String createItem(@ModelAttribute("news") final ItemDto dto) {
        newsService.createItem(dto);
        return "redirect:/news";
    }

    @PostMapping(value = {"{id}"})
    public String updateItem(@PathVariable("id") final UUID id, @ModelAttribute("news") final ItemDto dto) {
        newsService.updateItem(id, dto);
        return "redirect:/news";
    }

    @GetMapping(value = {"{id}/delete"})
    public String deleteItem(@PathVariable("id") final UUID id) {
        newsService.deleteItem(id);
        return "redirect:/news";
    }
}
