package com.example.hotelservice.authentication.user;

import com.example.hotelservice.authentication.role.Role;
import com.example.hotelservice.authentication.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);

        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsersByRole(String role) {

        Role myRole = roleRepository.findByRole(role);

        List<User> userList = userRepository.findAll();
        List<User> resUserList = new ArrayList<>();

        for (User user : userList) {
            System.out.println(user.getRoles().contains(myRole));
            if (user.getRoles().contains(myRole)){
                resUserList.add(user);
            }
        }

        System.out.println(resUserList);

        return resUserList;
    }

}
