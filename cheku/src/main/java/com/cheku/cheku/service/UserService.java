package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.ProcessedUser;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<ProcessedUser> getAllUsers() {
        return userRepository.getAllbyNameEmail();
    }

    public User addUser(User user) {
    //check if user already exists
        if(userRepository.findByEmail(user.getEmail()) != null){
            System.out.println("User already exists");
            return null;
        }

        //criar um grupo para o user
        Group group = new Group();
        if (groupRepository.findByName(user.getGroup_private()) != null) {
            System.out.println("Group already exists");
            return null;
        }
        group.setName(user.getGroup_private());
        group.getUserList().add(user);
        try{
            User user1 = userRepository.save(user);
            try{
                group.setAdmin(user1.getId());
                groupRepository.save(group);
                user1.getGroupList().add(group);
                return userRepository.save(user1);
            } catch (Exception e){
                System.out.println("Error saving group");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error saving User ");
            return null;
        }

    }
}
