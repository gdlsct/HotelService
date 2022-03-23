package com.example.hotelservice.service.impl;

import com.example.hotelservice.domain.Task;
import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.dto.TaskDTO;
import com.example.hotelservice.mapper.TaskMapper;
import com.example.hotelservice.repository.GuestRepository;
import com.example.hotelservice.repository.PersonBaseRepository;
import com.example.hotelservice.repository.StatusRepository;
import com.example.hotelservice.repository.TaskRepository;
import com.example.hotelservice.repository.WorkerRepository;
import com.example.hotelservice.service.TaskService;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final GuestRepository guestRepository;
    private final WorkerRepository workerRepository;
    private final PersonBaseRepository<Person> personBaseRepository;
    private final StatusRepository statusRepository;
    private final TaskMapper taskMapper;

    public List<TaskDTO> getAllTasks(final Principal principal) {
        Person person = personBaseRepository.findByLogin(principal.getName());

        if (person.getRole().getRoleName().equals("ROLE_ADMIN") || person.getRole().getRoleName().equals("ROLE_DISPATCHER")) {
            return taskRepository.findAllByOrderByTaskIdDesc()
                    .stream().map(taskMapper::mapEntityToDTO).collect(Collectors.toList());
        }
        if (person.getRole().getRoleName().equals("ROLE_WORKER")) {
            return taskRepository.findAllByWorkerLoginOrderByTaskIdDesc(person.getLogin())
                    .stream().map(taskMapper::mapEntityToWorkerDTO).collect(Collectors.toList());
        }
        if (person.getRole().getRoleName().equals("ROLE_GUEST")) {
            return taskRepository.findAllByGuestLoginOrderByTaskIdDesc(person.getLogin())
                    .stream().map(taskMapper::mapEntityToGuestDTO).collect(Collectors.toList());
        }

        throw new RuntimeException("Нет известных ролей");
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void createTask(final TaskDTO taskDTO, final Principal principal) {
        Task task = new Task();
        task.setGuest(guestRepository.findByLogin(principal.getName()));
        task.setDescription(taskDTO.getDescription());
        task.setStatus(statusRepository.findByStatusName("Создано"));
        taskRepository.save(task);
    }

    public Task newTask(final TaskDTO dto){
        Task task = new Task();
        task.setGuest(guestRepository.findByLogin("guest1"));
        task.setDescription(dto.getDescription());
        task.setStatus(statusRepository.findByStatusName("Создано"));

        return taskRepository.save(task);
    }

    public void updateTask(final Long id, final TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));

        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }

        if (dto.getWorkerLogin() != null && !dto.getWorkerLogin().isEmpty()) {
            task.setWorker(workerRepository.findByLogin(dto.getWorkerLogin()));
            task.setStatus(statusRepository.findByStatusName("Назначено"));
        }

        if (dto.getDispatcherComment() != null) {
            task.setDispatcherComment(dto.getDispatcherComment());
        }

        taskRepository.save(task);
    }

    public Task updateTaskById(final Long id, final TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));

        task.setDescription(dto.getDescription());

        return task;
    }

    public void cancelTask(final Long id, final Principal principal) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));
        Person person = personBaseRepository.findByLogin(principal.getName());

        if (person.getRole().getRoleName().equals("ROLE_ADMIN")
                || person.getRole().getRoleName().equals("ROLE_DISPATCHER")) {
            task.setStatus(statusRepository.findByStatusName("Отменено диспетчером"));
        }

        if (person.getRole().getRoleName().equals("ROLE_WORKER")) {
            task.setStatus(statusRepository.findByStatusName("Отменено сотрудником"));
        }

        if (person.getRole().getRoleName().equals("ROLE_GUEST")) {
            task.setStatus(statusRepository.findByStatusName("Отменено гостем"));
        }

        taskRepository.save(task);
    }

    public void successTask(final Long id, final TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));
        task.setStatus(statusRepository.findByStatusName("Выполнено"));
        task.setInventory(dto.getInventory());

        taskRepository.save(task);
    }

    public void rateTask(final Long id, final TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));
        task.setRating(dto.getRating());

        taskRepository.save(task);
    }

    public TaskDTO getTaskDTOById(final Long id, final Principal principal) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));
        Person person = personBaseRepository.findByLogin(principal.getName());

        if (person.getRole().getRoleName().equals("ROLE_ADMIN")
                || person.getRole().getRoleName().equals("ROLE_DISPATCHER")) {
            return taskMapper.mapEntityToDTO(task);
        }
        if (person.getRole().getRoleName().equals("ROLE_WORKER")) {
            return taskMapper.mapEntityToWorkerDTO(task);
        }
        if (person.getRole().getRoleName().equals("ROLE_GUEST")) {
            return taskMapper.mapEntityToGuestDTO(task);
        }

        throw new RuntimeException("Нет известных ролей");
    }

    public Task getTaskById(final Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));
    }
}
