package com.example.hotelservice.authentication.userRole;

import com.example.hotelservice.authentication.users.AppUser;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Override
    public List<UserRole> getAllUsers() {
        return null;
    }

    @Override
    public void updateUser(AppUser appUser, UserRole userRole) {

    }

    @Override
    public void newUser(AppUser appUser, UserRole userRole, Principal principal) {

    }

    @Override
    public UserRole getUserById(long id) {
        return null;
    }
}
