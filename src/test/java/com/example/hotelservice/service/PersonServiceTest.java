package com.example.hotelservice.service;

import com.example.hotelservice.domain.person.Admin;
import com.example.hotelservice.domain.person.Dispatcher;
import com.example.hotelservice.domain.person.Guest;
import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.domain.person.Worker;
import com.example.hotelservice.dto.NewPersonDTO;
import com.example.hotelservice.dto.PersonDTO;
import com.example.hotelservice.repository.AdminRepository;
import com.example.hotelservice.repository.DispatcherRepository;
import com.example.hotelservice.repository.GuestRepository;
import com.example.hotelservice.repository.PersonBaseRepository;
import com.example.hotelservice.repository.RoleRepository;
import com.example.hotelservice.repository.WorkerRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
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
public class PersonServiceTest {

    @MockBean
    private PersonBaseRepository<Person> personBaseRepository;

    @MockBean
    private GuestRepository guestRepository;

    @MockBean
    private DispatcherRepository dispatcherRepository;

    @MockBean
    private WorkerRepository workerRepository;

    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleRepository roleRepository;

    private Guest guest1;
    private Guest guest2;
    private Worker worker;
    private Dispatcher dispatcher;
    private Admin admin;

    @BeforeEach
    void init() {
        guest1 = new Guest();
        guest1.setPersonId(UUID.fromString("76a13daa-8dc0-11ec-b909-0242ac120002"));
        guest1.setLogin("TestGuest1");
        guest1.setPassword("TestGuest1");
        guest1.setFirstName("TestGuest1");
        guest1.setLastName("TestGuest1");
        guest1.setRole(roleRepository.findByRoleName("ROLE_GUEST"));
        guest1.setIsActive(true);

        guest2 = new Guest();
        guest2.setPersonId(UUID.fromString("76a13efe-8dc0-11ec-b909-0242ac120002"));
        guest2.setLogin("TestGuest2");
        guest2.setPassword("TestGuest2");
        guest2.setFirstName("TestGuest2");
        guest2.setLastName("TestGuest2");
        guest2.setRole(roleRepository.findByRoleName("ROLE_GUEST"));
        guest2.setIsActive(true);

        dispatcher = new Dispatcher();
        dispatcher.setPersonId(UUID.fromString("76a13daa-8dc0-11ec-b909-0242ac120003"));
        dispatcher.setLogin("TestDispatcher");
        dispatcher.setPassword("TestDispatcher");
        dispatcher.setFirstName("TestDispatcher");
        dispatcher.setLastName("TestDispatcher");
        dispatcher.setRole(roleRepository.findByRoleName("ROLE_DISPATCHER"));
        dispatcher.setIsActive(true);

        worker = new Worker();
        worker.setPersonId(UUID.fromString("76a13daa-8dc0-11ec-b909-0242ac120003"));
        worker.setLogin("TestWorker");
        worker.setPassword("TestWorker");
        worker.setFirstName("TestWorker");
        worker.setLastName("TestWorker");
        worker.setRole(roleRepository.findByRoleName("ROLE_WORKER"));
        worker.setIsActive(true);

        admin = new Admin();
        admin.setPersonId(UUID.fromString("76a13daa-8dc0-11ec-b909-0242ac120003"));
        admin.setLogin("TestAdmin");
        admin.setPassword("TestAdmin");
        admin.setFirstName("TestAdmin");
        admin.setLastName("TestAdmin");
        admin.setRole(roleRepository.findByRoleName("ROLE_ADMIN"));
        admin.setIsActive(true);
    }

    @Test
    @DisplayName("Test create person")
    public void testPersonTask() {

        NewPersonDTO dto = new NewPersonDTO();
        dto.setLogin("TestGuest1");
        dto.setPassword("TestGuest1");
        dto.setFirstName("TestGuest1");
        dto.setLastName("TestGuest1");
        dto.setRole("ROLE_GUEST");

        when(guestRepository.save(any())).thenReturn(guest1);

        Guest created = (Guest) personService.savePerson(dto);

        assertEquals("TestGuest1", created.getLogin());
        verify(guestRepository, times(1)).save(any());
    }

    //
    @Test
    @DisplayName("Test find all persons by role")
    void testFindAllByRole() {
        when(personBaseRepository.findAllByRole(any())).thenReturn(Arrays.asList(guest1, guest2));

        List<PersonDTO> allPersons = personService.findPersonsByRole(roleRepository.findByRoleName("ROLE_GUEST"));

        assertEquals(2, allPersons.size());
        assertEquals("TestGuest1", allPersons.get(0).getLogin());
        assertEquals("TestGuest2", allPersons.get(1).getLogin());

        verify(personBaseRepository, times(1)).findAllByRole(any());
    }

    @Test
    @DisplayName("Test find all persons")
    void testFindAll() {

        when(adminRepository.findAll()).thenReturn(Collections.singletonList(admin));
        when(workerRepository.findAll()).thenReturn(Collections.singletonList(worker));
        when(dispatcherRepository.findAll()).thenReturn(Collections.singletonList(dispatcher));
        when(guestRepository.findAll()).thenReturn(Arrays.asList(guest1, guest2));

        List<PersonDTO> allPersons = personService.findAllPersons();

        assertEquals(5, allPersons.size());
        assertEquals("TestAdmin", allPersons.get(0).getLogin());
        assertEquals("TestGuest1", allPersons.get(1).getLogin());
        assertEquals("TestGuest2", allPersons.get(2).getLogin());
        assertEquals("TestDispatcher", allPersons.get(3).getLogin());
        assertEquals("TestWorker", allPersons.get(4).getLogin());

        verify(adminRepository, times(1)).findAll();
        verify(workerRepository, times(1)).findAll();
        verify(dispatcherRepository, times(1)).findAll();
        verify(guestRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test find person by login")
    public void testFindPersonByLogin() {
        when(guestRepository.findByLogin(any())).thenReturn(guest1);

        PersonDTO person = personService.findPersonByLogin("TestGuest1");

        assertEquals("TestGuest1", person.getLogin());

        verify(guestRepository, times(1)).findByLogin(any());
    }
}
