package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group addGroup(Group group) {
        try {
            List<Car> cars = new ArrayList<>();
            group.setCarList(cars);

            List<User> users = new ArrayList<>();
            group.setUsers(users);

            return groupRepository.save(group);
        } catch (Exception e) {
            System.out.println("Error saving group");
            return null;
        }
    }
}
