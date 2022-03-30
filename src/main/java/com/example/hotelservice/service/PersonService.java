package com.example.hotelservice.service;

import com.example.hotelservice.domain.Role;
import com.example.hotelservice.domain.person.Person;
import com.example.hotelservice.dto.NewPersonDTO;
import com.example.hotelservice.dto.PersonDTO;
import java.util.List;

public interface PersonService {

    PersonDTO findPersonByLogin(String login);

    Person savePerson(NewPersonDTO dto);

    List<PersonDTO> findAllPersons();

    List<PersonDTO> findPersonsByRole(Role role);
}
