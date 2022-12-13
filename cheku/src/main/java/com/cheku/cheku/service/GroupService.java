package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group addGroup(Group group) {

        //check if group already exists
        if(groupRepository.findByName(group.getName()) != null){
            System.out.println("Group already exists");
            return null;
        }

        //check if user exists
        if(userRepository.findById(group.getAdmin()) == null){
            System.out.println("User does not exist");
            return null;
        }

        long id = group.getAdmin();
        userRepository.findById(id).get().getGroupList().add(group);
        group.getUserList().add(userRepository.findById(id).get());
        groupRepository.save(group);
        return groupRepository.save(group);
    }

    public List<Car> addCarToGroup(Long group_id, Long car_id) {
        //check if group exists
        if(groupRepository.findById(group_id) == null){
            System.out.println("Group does not exist");
            return null;
        }

        //check if car exists
        if(carRepository.findById(car_id) == null){
            System.out.println("Car does not exist");
            return null;
        }

        Group group = groupRepository.findById(group_id).get();
        Car car = carRepository.findById(car_id).get();
        group.getCarList().add(car);
        car.setGroup(group);
        groupRepository.save(group);
        carRepository.save(car);
        return ListCarInGroup(group_id);
    }

    private List<Car> ListCarInGroup(Long group_id) {
        return groupRepository.findById(group_id).get().getCarList();
    }

    public Boolean verifyAdmin(Long user_id, Long group_id) {
        //check if user exists
        if(userRepository.findById(user_id) == null){
            System.out.println("User does not exist");
            return false;
        }

        //check if group exists
        if(groupRepository.findById(group_id) == null){
            System.out.println("Group does not exist");
            return false;
        }

        return groupRepository.findById(group_id).get().getAdmin() == user_id;

    }

    public void deleteGroup(Long group_id) {
        groupRepository.deleteById(group_id);
    }

    public List<Group> getUserGroups(Long user_id) {
        //check if user exists
        if(userRepository.findById(user_id) == null){
            System.out.println("User does not exist");
            return null;
        }

        return userRepository.findById(user_id).get().getGroupList();
    }
}
