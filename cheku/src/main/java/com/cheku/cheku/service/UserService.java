package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
    //check if user already exists
        if(userRepository.findByEmail(user.getEmail()) != null){
            System.out.println("User already exists");
            return null;
        }
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error saving user");
            return null;
        }
    }

}
