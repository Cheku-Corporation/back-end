package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.NamesGroup;
import com.cheku.cheku.auxiliar_classes.ProcessedUser;
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

    public ProcessedUser addUser(User user) {
    //check if user already exists
        if(userRepository.findByEmail(user.getEmail()) != null){
            System.out.println("User already exists");
            throw new RuntimeException("User already exists");
        }
        //check if group exists
        if(groupRepository.findByName(user.getGroup_private()) != null){
            System.out.println("Group already exist");
            return null;
        }

        //create group
        Group group = new Group();
        group.setName(user.getGroup_private());


        try {
            User new_user = userRepository.save(user);
            try {
                group.setAdmin(new_user.getId());
                group.getUserList().add(new_user);
                new_user.getGroupList().add(group);
                groupRepository.save(group);
                return userRepository.getUserbyNameEmail(user.getEmail(), user.getName());
            } catch (Exception e) {
                System.out.println("Error saving group");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error saving user, "+ e);
            return null;
        }
    }
}
