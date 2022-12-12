package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.model.request.GroupCreateRequest;
import com.cheku.cheku.repository.*;
import org.springframework.beans.BeanUtils;
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

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group createGroup(GroupCreateRequest group) {
        Optional<ApiUser> user = userRepository.findById(group.getAdmin());
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        //check if group already exists
        if(groupRepository.findByName(group.getName()) != null){
            throw new RuntimeException("Group already exists");
        }

        Group groupToCreate = new Group();
        BeanUtils.copyProperties(group, groupToCreate);
        groupToCreate.setAdmin(user.get().getId());
        groupToCreate.addUser(user.get());
        return groupRepository.save(groupToCreate);
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
