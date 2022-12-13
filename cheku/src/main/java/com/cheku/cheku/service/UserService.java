package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.ProcessedUser;
import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<ProcessedUser> getAllUsers() {
        return userRepository.getAllbyNameEmail();
    }

    public User addUser(User user) throws ResourceNotFoundException {
    //check if user already exists
        if(userRepository.findByEmail(user.getEmail()) != null){
            System.out.println("User already exists");
            throw new ResourceNotFoundException("User already exists");
        }
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error saving user");
            throw new ResourceNotFoundException("Error saving user");
        }
    }
}
