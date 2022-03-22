package com.example.hotelservice.controller;

import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.dto.NewPersonDTO;
import com.example.hotelservice.repository.PersonBaseRepository;
import com.example.hotelservice.repository.RoleRepository;
import com.example.hotelservice.service.PersonService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final RoleRepository roleRepository;
    private final PersonBaseRepository<Person> personBaseRepository;

    @GetMapping
    public String persons(Model model, Principal principal) {
        model.addAttribute("persons", personService.findAllPersons());
        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));

        return "admin/list";
    }

    @GetMapping(value = {"/new"})
    public String showRegistrationForm(Model model, Principal principal) {
        NewPersonDTO newPersonDTO = new NewPersonDTO();

        model.addAttribute("newPerson", newPersonDTO);
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));

        return "admin/registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("persons") NewPersonDTO newPersonDTO) {
        personService.savePerson(newPersonDTO);
        return "redirect:/persons";
    }

}
