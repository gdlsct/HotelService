package com.example.hotelservice.mapper;

import com.example.hotelservice.domain.person.Admin;
import com.example.hotelservice.domain.person.Dispatcher;
import com.example.hotelservice.domain.person.Guest;
import com.example.hotelservice.domain.person.Worker;
import com.example.hotelservice.dto.PersonDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    PersonDTO mapPersonToAdminDto(Admin admin);

    PersonDTO mapPersonToGuestDto(Guest guest);

    PersonDTO mapPersonToDispatcherDto(Dispatcher dispatcher);

    PersonDTO mapPersonToWorkerDto(Worker worker);
}
