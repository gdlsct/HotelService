//package com.example.hotelservice.service;
//
//import com.example.hotelservice.domain.Task;
//import com.example.hotelservice.repository.TaskRepository;
//import java.security.Principal;
//import java.util.Arrays;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
//
//@SpringBootTest
//public class TaskServiceTest {
//
//    @MockBean
//    private TaskRepository taskRepository;
//
////    @MockBean
////    private AddressRepository addressRepository;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Test
//    @DisplayName("Test find all tasks")
//    void testFindAll() {
//
//        Task task1 = new Task();
//        Task task2 = new Task();
//
//        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
//
//        Principal principal = new Principal() {
//            @Override
//            public String getName() {
//                return null;
//            }
//        }
//
//        List<UserResponse> tasks = taskService.getAllTasks();
//
//        assertEquals(2, users.size());
//        assertEquals("John", users.get(0).getName());
//        assertEquals("Jack", users.get(1).getName());
//
//        verify(taskRepository, times(1)).findAll();
//    }
//
//
//    @Test
//    @DisplayName("Test find user by id")
//    void testFindById() throws NotFoundException {
//
//        when(taskRepository.findById(0L)).thenReturn(java.util.Optional.of(new User("John", "88005553535")));
//
//        UserResponse userById = taskService.getUserById(0L);
//
//        assertEquals("John", userById.getName());
//        verify(taskRepository, times(1)).findById(0L);
//    }
//
//    @Test
//    @DisplayName("Test create user")
//    public void testCreateUser() throws NotFoundException {
//
//        UserNameNumber userNameNumber = new UserNameNumber("John", "88005553535");
//
//        when(taskRepository.save(any())).thenReturn(new User("John", "88005553535"));
//
//        UserNameNumber created = taskService.createUser(userNameNumber);
//
//        assertEquals("John", created.getName());
//        verify(taskRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Test update user")
//    public void testUpdateUser() throws NotFoundException {
//
//        UserNameNumber userNameNumber = new UserNameNumber("John", "88005553535");
//
//
//        when(taskRepository.findById(0L)).thenReturn(java.util.Optional.of(new User("Steve", "88005553536")));
//        when(taskRepository.save(any())).thenReturn(new User("John", "88005553535"));
//
//        UserResponse updated = taskService.updateUserById(0L, userNameNumber);
//
//        assertEquals("John", updated.getName());
//
//        verify(taskRepository, times(1)).findById(0L);
//        verify(taskRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Test delete user")
//    public void testDeleteUser() {
//
//        taskService.deleteUserById(0L);
//
//        verify(taskRepository, times(1)).deleteById(0L);
//    }
//
//    @Test
//    @DisplayName("Test add address to user")
//    public void testAddAddressToUser() throws NotFoundException {
//
//        User user = new User("Steve", "88005553536");
//        Address address = new Address("Lenina", 1);
//
//        when(taskRepository.findById(1L)).
//                thenReturn(java.util.Optional.of(user));
//
//        when(addressRepository.findById(1L)).
//                thenReturn(java.util.Optional.of(address));
//
//        when(taskRepository.save(any())).
//                thenReturn(user);
//
//        UserResponse userResponse = taskService.addAddressToUser(1L, 1L);
//
//        AddressStreetNumber addressStreetNumber = AddressStreetNumber.from(address);
//
//        assertEquals(true, userResponse.getAddresses().contains(addressStreetNumber));
//
//
//        verify(taskRepository, times(1)).findById(1L);
//        verify(addressRepository, times(1)).findById(1L);
//        verify(taskRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Test delete address from user")
//    public void testDeleteAddressFromUser() throws NotFoundException {
//
//        User user = new User("Steve", "88005553536");
//        Address address = new Address("Lenina", 1);
//        user.addAddress(address);
//
//        when(taskRepository.findById(1L)).
//                thenReturn(java.util.Optional.of(user));
//
//        when(addressRepository.findById(1L)).
//                thenReturn(java.util.Optional.of(address));
//
//        when(taskRepository.save(any())).
//                thenReturn(user);
//
//        UserResponse userResponse = taskService.deleteAddressFromUser(1L, 1L);
//        AddressStreetNumber addressStreetNumber = AddressStreetNumber.from(address);
//
//        assertEquals(false, userResponse.getAddresses().contains(addressStreetNumber));
//
//        verify(taskRepository, times(1)).findById(1L);
//        verify(addressRepository, times(1)).findById(1L);
//        verify(taskRepository, times(1)).save(any());
//    }
//}
