package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.NamesGroup;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public List<NamesGroup> getAllGroups() {
        return groupRepository.getAllbyNameString();
    }

    public NamesGroup addGroup(Group group) {

        //check if group already exists
        if(groupRepository.findByName(group.getName()) != null){
            System.out.println("Group already exists");
            throw new RuntimeException("Group already exists");
        }

        //check if user exists
        if(userRepository.findById(group.getAdmin()) == null){
            System.out.println("User does not exist");
            return null;
        }

        try {
            //Criar o group
            long id = group.getAdmin();
            userRepository.findById(id).get().getGroupList().add(group);
            group.getUserList().add(userRepository.findById(id).get());
            groupRepository.save(group);
            return groupRepository.getGroupbyNameString(group.getName());
        } catch (Exception e) {
            System.out.println("Error creating group");
            throw new RuntimeException("Error creating group");
        }
    }

    public List<Car> addCarToGroup(Long group_id, Long car_id) {
        //check if group exists
        if(groupRepository.findById(group_id) == null){
            System.out.println("Group does not exist");
            return null;
        }

        //check if car exists
        if(userRepository.findById(car_id) == null){
            System.out.println("Car does not exist");
            return null;
        }

        Group group = groupRepository.findById(group_id).get();
        Car car = carRepository.findById(car_id).get();
        group.getCarList().add(car);
        car.getGroupList().add(group);
        groupRepository.save(group);
        carRepository.save(car);
        return ListCarInGroup(group_id);
    }

    private List<Car> ListCarInGroup(Long group_id) {
        return groupRepository.findById(group_id).get().getCarList();
    }
}
