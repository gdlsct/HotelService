package com.example.hotelservice.service.impl;

import com.example.hotelservice.domain.Role;
import com.example.hotelservice.dto.PersonDTO;
import com.example.hotelservice.service.PersonService;
import java.util.Collections;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonService personService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        PersonDTO person = personService.findPersonByLogin(userName);
        if (person == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        GrantedAuthority authority = getUserAuthority(person.getRole());
        return buildUserForAuthentication(person, authority);
    }

    private GrantedAuthority getUserAuthority(Role role) {
        return new SimpleGrantedAuthority(role.getRoleName());
    }

    private UserDetails buildUserForAuthentication(PersonDTO person, GrantedAuthority authority) {
        return new User(person.getLogin(), person.getPassword(),
                person.getIsActive(), true, true, true, Collections.singleton(authority));
    }
}