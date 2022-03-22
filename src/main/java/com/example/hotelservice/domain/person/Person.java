package com.example.hotelservice.domain.person;

import com.example.hotelservice.domain.Role;
import com.example.hotelservice.dto.NewPersonDTO;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "persons")
public abstract class Person {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID personId;

    private String imageUrl = "https://cdn-icons-png.flaticon.com/512/1946/1946429.png";

    @Column(unique = true)
    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public static Admin createAdmin(NewPersonDTO dto, Role role) {
        Admin admin = new Admin();
        admin.setLogin(dto.getLogin());
        admin.setPassword(dto.getPassword());
        admin.setIsActive(true);
        admin.setFirstName(dto.getFirstName());
        admin.setLastName(dto.getLastName());
        admin.setRole(role);

        return admin;
    }

    public static Dispatcher createDispatcher(final NewPersonDTO dto, Role role) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setLogin(dto.getLogin());
        dispatcher.setPassword(dto.getPassword());
        dispatcher.setIsActive(true);
        dispatcher.setFirstName(dto.getFirstName());
        dispatcher.setLastName(dto.getLastName());
        dispatcher.setRole(role);

        return dispatcher;
    }

    public static Worker createWorker(final NewPersonDTO dto, Role role) {
        Worker worker = new Worker();
        worker.setLogin(dto.getLogin());
        worker.setPassword(dto.getPassword());
        worker.setIsActive(true);
        worker.setFirstName(dto.getFirstName());
        worker.setLastName(dto.getLastName());
        worker.setRole(role);

        return worker;
    }

    public static Guest createGuest(final NewPersonDTO dto, Role role) {
        Guest guest = new Guest();
        guest.setLogin(dto.getLogin());
        guest.setPassword(dto.getPassword());
        guest.setIsActive(true);
        guest.setFirstName(dto.getFirstName());
        guest.setLastName(dto.getLastName());
        guest.setRoom(dto.getRoom());
        guest.setRole(role);

        return guest;
    }
}
