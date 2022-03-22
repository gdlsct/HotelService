//package com.example.hotelservice.mapper;
//
//import com.example.hotelservice.domain.person.Admin;
//import com.example.hotelservice.domain.person.Dispatcher;
//import com.example.hotelservice.domain.person.Guest;
//import com.example.hotelservice.domain.person.Worker;
//import com.example.hotelservice.dto.PersonDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//public class PersonMapperDecorator implements PersonMapper{
//
//    @Autowired
//    @Qualifier("delegate")
//    private PersonMapper delegate;
//
//    @Override
//    public PersonDTO mapPersonToAdminDto(Admin admin) {
//        PersonDTO dto = new PersonDTO();
//        dto.setPersonId(admin.getPersonId());
//        dto.setFirstName(admin.getFirstName());
//        dto.setLastName(admin.getLastName());
//        dto.setLogin(admin.getLogin());
//        dto.setPassword(admin.getPassword());
//        dto.setRole();
//
//        return dto;
//    }
//
//    @Override
//    public PersonDTO mapPersonToGuestDto(Guest guest) {
//        return delegate.
//    }
//
//    @Override
//    public PersonDTO mapPersonToDispatcherDto(Dispatcher dispatcher) {
//        return null;
//    }
//
//    @Override
//    public PersonDTO mapPersonToWorkerDto(Worker worker) {
//        return null;
//    }
//}
