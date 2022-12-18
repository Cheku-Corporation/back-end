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

    //create group
    public Group createGroup(GroupCreateRequest group) {
        Optional<ApiUser> user = userRepository.findById(group.getAdmin());
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        //check if group already exists
        if(groupRepository.findByGroupName(group.getGroupName()) != null){
            throw new RuntimeException("Group already exists");
        }

        Group groupToCreate = new Group();
        BeanUtils.copyProperties(group, groupToCreate);
        groupToCreate.setAdmin(user.get().getId());
        groupToCreate.addUser(user.get());
        user.get().setGroup(groupToCreate);
        return groupRepository.save(groupToCreate);
    }

    //add user to group
    public void addUserToGroup(Long idUser, long idGroup) {
        //check if user exists
        if(userRepository.findById(idUser) == null){
            System.out.println("User does not exist");
            return;
        }

        //check if group exists
        if(groupRepository.findById(idGroup) == null){
            System.out.println("Group does not exist");
            return;
        }

        Group group = groupRepository.findById(idGroup).get();
        ApiUser user = userRepository.findById(idUser).get();
        group.addUser(user);
        user.setGroup(group);
        groupRepository.save(group);
        userRepository.save(user);
    }

    public boolean findGroupByName(String groupName) {
        return groupRepository.findByGroupName(groupName) != null;
    }

    public boolean findGroupById(long parseLong) {
        return groupRepository.findById(parseLong) != null;
    }
    public void deleteGroup(Long group_id) {
        groupRepository.deleteById(group_id);
    }

    public List<Car> ListCarInGroup(Long group_id) {
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


}
