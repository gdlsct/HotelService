package com.example.hotelservice.authentication.userRole;

import com.example.hotelservice.authentication.users.AppUser;

import java.security.Principal;
import java.util.List;

public interface UserRoleService {

    List<UserRole> getAllUsers();

    void updateUser(AppUser appUser, UserRole userRole);

    void newUser(AppUser appUser, UserRole userRole, Principal principal);

    UserRole getUserById(long id);
}
