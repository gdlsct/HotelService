package com.example.hotelservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "guests")
@Data
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "guest_name")
    private String name;

    @Column(name = "guest_surname")
    private String surname;

    @Column(name = "guest_room")
    private int room;

    @Transient
    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Request> requests;
}
