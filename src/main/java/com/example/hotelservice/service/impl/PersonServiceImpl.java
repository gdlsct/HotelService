package com.example.hotelservice.service.impl;

import com.example.hotelservice.domain.Role;
import com.example.hotelservice.domain.person.Admin;
import com.example.hotelservice.domain.person.Dispatcher;
import com.example.hotelservice.domain.person.Guest;
import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.domain.person.Worker;
import com.example.hotelservice.dto.NewPersonDTO;
import com.example.hotelservice.dto.PersonDTO;
import com.example.hotelservice.mapper.PersonMapper;
import com.example.hotelservice.repository.AdminRepository;
import com.example.hotelservice.repository.DispatcherRepository;
import com.example.hotelservice.repository.GuestRepository;
import com.example.hotelservice.repository.PersonBaseRepository;
import com.example.hotelservice.repository.RoleRepository;
import com.example.hotelservice.repository.WorkerRepository;
import com.example.hotelservice.service.PersonService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonBaseRepository<Person> personBaseRepository;
    private final GuestRepository guestRepository;
    private final DispatcherRepository dispatcherRepository;
    private final WorkerRepository workerRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PersonMapper personMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonDTO findPersonByLogin(String login) {
        Admin admin = adminRepository.findByLogin(login);
        if (admin != null) {
            return personMapper.mapPersonToAdminDto(admin);
        }

        Dispatcher dispatcher = dispatcherRepository.findByLogin(login);
        if (dispatcher != null) {
            return personMapper.mapPersonToDispatcherDto(dispatcher);
        }

        Guest guest = guestRepository.findByLogin(login);
        if (guest != null) {
            return personMapper.mapPersonToGuestDto(guest);
        }

        Worker worker = workerRepository.findByLogin(login);
        if (worker != null) {
            return personMapper.mapPersonToWorkerDto(worker);
        }

        return null;
    }

    public Person savePerson(final NewPersonDTO dto) {
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRoleName(dto.getRole());
        switch (dto.getRole()) {
            case "ROLE_GUEST":
                return guestRepository.save(Person.createGuest(dto, role));
            case "ROLE_DISPATCHER":
                return dispatcherRepository.save(Person.createDispatcher(dto, role));
            case "ROLE_WORKER":
                return workerRepository.save(Person.createWorker(dto,role));
            case "ROLE_ADMIN":
                return adminRepository.save(Person.createAdmin(dto, role));
            default:
                throw new RuntimeException("Роль не найдена");
        }
    }

    public List<PersonDTO> findAllPersons() {
        List<PersonDTO> personDTOS = new ArrayList<>();

        personDTOS.addAll(adminRepository.findAll()
                .stream().map(personMapper::mapPersonToAdminDto).collect(Collectors.toList()));
        personDTOS.addAll(guestRepository.findAll()
                .stream().map(personMapper::mapPersonToGuestDto).collect(Collectors.toList()));
        personDTOS.addAll(dispatcherRepository.findAll()
                .stream().map(personMapper::mapPersonToDispatcherDto).collect(Collectors.toList()));
        personDTOS.addAll(workerRepository.findAll()
                .stream().map(personMapper::mapPersonToWorkerDto).collect(Collectors.toList()));

        return personDTOS;
    }

    public List<PersonDTO> findPersonsByRole(Role role) {
        return personBaseRepository.findAllByRole(role).stream().map(p -> {
            if (p.getRole().getRoleName().equals("ROLE_ADMIN") || p.getRole().getRoleName().equals("ROLE_DISPATCHER")) {
                return personMapper.mapPersonToDispatcherDto((Dispatcher) p);
            }
            if (p.getRole().getRoleName().equals("ROLE_WORKER")) {
                return personMapper.mapPersonToWorkerDto((Worker) p);
            }
            if (p.getRole().getRoleName().equals("ROLE_GUEST")) {
                return personMapper.mapPersonToGuestDto((Guest) p);
            }
            throw new RuntimeException("Нет известных ролей");
        }).collect(Collectors.toList());
    }
}
