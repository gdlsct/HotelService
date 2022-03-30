package com.example.hotelservice.service;

import com.example.hotelservice.domain.Task;
import com.example.hotelservice.dto.TaskDTO;
import com.example.hotelservice.repository.TaskRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    private Task task1;
    private Task task2;

    @BeforeEach
    void init(){
        task1 = new Task();
        task1.setTaskId(1L);
        task1.setDescription("a");

        task2 = new Task();
        task2.setTaskId(2L);
    }

    @Test
    @DisplayName("Test create task")
    public void testCreateTask() {

        TaskDTO dto = new TaskDTO();
        dto.setDescription("a");

        when(taskRepository.save(any())).thenReturn(task1);

        Task created = taskService.newTask(dto);

        assertEquals("a", created.getDescription());
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test find all tasks")
    void testFindAll() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> allTasks = taskService.getTasks();

        assertEquals(2, allTasks.size());
        assertEquals(1L, allTasks.get(0).getTaskId());
        assertEquals(2L, allTasks.get(1).getTaskId());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test find task by id")
    void testFindById() {

        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task1));

        Task task = taskService.getTaskById(1L);

        assertEquals(1L, task.getTaskId());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test update task")
    public void testUpdateUser() {

        TaskDTO dto = new TaskDTO();
        dto.setDescription("b");

        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task1));

        Task updated = taskService.updateTaskById(1L, dto);

        assertEquals("b", updated.getDescription());

        verify(taskRepository, times(1)).findById(1L);
    }
}
