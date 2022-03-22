package com.example.hotelservice.controller;

import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.dto.PersonDTO;
import com.example.hotelservice.dto.TaskDTO;
import com.example.hotelservice.mapper.PersonMapper;
import com.example.hotelservice.repository.GuestRepository;
import com.example.hotelservice.repository.PersonBaseRepository;
import com.example.hotelservice.repository.RoleRepository;
import com.example.hotelservice.service.PersonService;
import com.example.hotelservice.service.TaskService;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final PersonService personService;
    private final GuestRepository guestRepository;
    private final PersonMapper personMapper;
    private final RoleRepository roleRepository;
    private final PersonBaseRepository<Person> personBaseRepository;

    @GetMapping
    public String getTasksList(final Model model, final Principal principal) {
        Person person = personBaseRepository.findByLogin(principal.getName());
        model.addAttribute("tasks", taskService.getAllTasks(principal));
        model.addAttribute("person", person);

        if (person.getRole().getRoleName().equals("ROLE_ADMIN") || person.getRole().getRoleName().equals("ROLE_DISPATCHER")) {
            return "dispatcher/tasks";
        }
        if (person.getRole().getRoleName().equals("ROLE_WORKER")) {
            return "worker/tasks";
        }
        if (person.getRole().getRoleName().equals("ROLE_GUEST")) {
            return "guest/list";
        }

        throw new RuntimeException("Нет известных ролей");
    }

    @GetMapping(value = {"/new"})
    public String getCreateTaskForm(final Model model, final Principal principal) {
        PersonDTO guest = personMapper.mapPersonToGuestDto(guestRepository.findByLogin(principal.getName()));

        model.addAttribute("task", TaskDTO.getNewDTO(guest));
        model.addAttribute("person", guest);

        return "guest/new";
    }

    @PostMapping
    public String createTask(@ModelAttribute("task") final TaskDTO taskDTO, final Principal principal) {
        taskService.createTask(taskDTO, principal);

        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getReadTaskForm(@PathVariable("id") final Long id, final Model model, final Principal principal) {
        Person person = personBaseRepository.findByLogin(principal.getName());
        model.addAttribute("task", taskService.getTaskDTOById(id, principal));
        model.addAttribute("person", person);

        if (person.getRole().getRoleName().equals("ROLE_ADMIN") || person.getRole().getRoleName().equals("ROLE_DISPATCHER")) {
            model.addAttribute("workers", personService.findPersonsByRole(roleRepository.findByRoleName("ROLE_WORKER")));
            return "dispatcher/view";
        }
        if (person.getRole().getRoleName().equals("ROLE_WORKER")) {
            return "worker/view";
        }
        if (person.getRole().getRoleName().equals("ROLE_GUEST")) {
            return "guest/view";
        }

        throw new RuntimeException("Нет известных ролей");
    }

    @GetMapping("/{id}/edit")
    public String getUpdateTaskForm(@PathVariable("id") final Long id, final Model model, final Principal principal) {
        Person person = personBaseRepository.findByLogin(principal.getName());
        model.addAttribute("task", taskService.getTaskDTOById(id, principal));
        model.addAttribute("person", person);

        if (person.getRole().getRoleName().equals("ROLE_ADMIN") || person.getRole().getRoleName().equals("ROLE_DISPATCHER")) {
            model.addAttribute("workers", personService.findPersonsByRole(roleRepository.findByRoleName("ROLE_WORKER")));
            return "dispatcher/edit";
        }
        if (person.getRole().getRoleName().equals("ROLE_WORKER")) {
            return "worker/edit";
        }
        if (person.getRole().getRoleName().equals("ROLE_GUEST")) {
            return "guest/edit";
        }

        throw new RuntimeException("Нет известных ролей");
    }

    @PostMapping(value = "/{id}")
    public String updateTask(@PathVariable("id") final Long id, @ModelAttribute("task") final TaskDTO dto) {
        taskService.updateTask(id, dto);

        return "redirect:/list";
    }

    @GetMapping("/{id}/cancel")
    public String cancelTask(@PathVariable("id") final Long id, final Principal principal) {
        taskService.cancelTask(id, principal);

        return "redirect:/list";
    }

    @PostMapping("/{id}/success")
    public String successTask(@PathVariable("id") final Long id, @ModelAttribute("task") final TaskDTO dto) {
        taskService.successTask(id, dto);

        return "redirect:/list";
    }

    @GetMapping("/{id}/rate")
    public String getRateTaskForm(@PathVariable("id") final Long id, final Model model, Principal principal) {
        model.addAttribute("task", taskService.getTaskDTOById(id, principal));
        model.addAttribute("person", personBaseRepository.findByLogin(principal.getName()));

        return "guest/rate";
    }

    @PostMapping("/{id}/rate")
    public String rateTask(@PathVariable("id") final Long id, @ModelAttribute("task") final TaskDTO dto) {
        taskService.rateTask(id, dto);

        return "redirect:/list";
    }
}
