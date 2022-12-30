package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.model.dto.UserDTO;
import com.cheku.cheku.model.request.GroupCreateRequest;
import com.cheku.cheku.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Base64;

@Service
public class GroupService {
    // Mapper for converting objects to and from different types
    @Autowired
    private ModelMapper modelMapper;

    // Repositories for accessing data in the database
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CarRepository carRepository;

    /** Returns a list of all groups in the database */
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    /** Creates a new group in the database
     * @param groupCreateRequest The request containing the group information to be created
     * @return The newly created group
     */
    public Group createGroup(GroupCreateRequest group) {
        // Check if the user exists
        Optional<ApiUser> user = userRepository.findById(group.getAdmin());
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        // Check if the group already exists
        if(groupRepository.findByGroupName(group.getGroupName()) != null){
            throw new RuntimeException("Group already exists");
        }

        Group groupToCreate = new Group();
        // Copy the group details from the request to the group object
        BeanUtils.copyProperties(group, groupToCreate);
        groupToCreate.setIsAdmin(user.get().getId());
        groupToCreate.addUser(user.get());
        String groupNameEncripty = encodeGroupName(groupToCreate.getGroupName());
        groupToCreate.setGroupNameEncripted(groupNameEncripty);
        user.get().setGroup(groupToCreate);
        return groupRepository.save(groupToCreate);
    }

    /** Returns a code for the group name
     * @param id The id of the group to be returned
     * @return The code for the group name
     */
    private String encodeGroupName(String groupName) {
        return Base64.getEncoder().encodeToString(groupName.getBytes());
    }

    /** Returns a true if the group name is valid
     * @param groupName The name of the group to be checked
     * @return True if the group name is valid
     */
    public boolean findGroupByCode(String groupNameEncripted) {
        return groupRepository.findByGroupNameEncripted(groupNameEncripted) != null;
    }

    /** Adds a user to a group
     * @param groupId The id of the group to add the user to
     * @param userId The id of the user to add to the group
     */
    public void addUserToGroup(Long idUser, String idGroup) {
        //check if user exists
        if(userRepository.findById(idUser) == null){
            System.out.println("User does not exist");
            return;
        }

        //check if group exists
        if(groupRepository.findByGroupNameEncripted(idGroup) == null){
            System.out.println("Group does not exist");
            return;
        }

        Group group = groupRepository.findByGroupNameEncripted(idGroup);
        ApiUser user = userRepository.findById(idUser).get();
        group.addUser(user);
        user.setGroup(group);
        //user.addGroup(group);
        groupRepository.save(group);
        userRepository.save(user);
    }

    /** Returns true if a group with the specified name exists, false otherwise */
    public boolean findGroupByName(String groupName) {
        return groupRepository.findByGroupName(groupName) != null;
    }

    /** Returns true if a group with the specified id exists, false otherwise */
    public boolean findGroupById(long parseLong) {
        return groupRepository.findById(parseLong) != null;
    }

    /** Deletes the group with the specified id */
    public void deleteGroup(Long group_id) {
        groupRepository.deleteById(group_id);
    }

    /** Returns a list of cars in the group with the specified id */
    public List<Car> ListCarInGroup(Long group_id) {
        return groupRepository.findById(group_id).get().getCarList();
    }


    /** Returns true if the user with the specified id is the admin of the group with the specified id, false otherwise */
    public Boolean verifyAdmin(Long user_id, Long group_id) {
        // Check if the user exists
        if(userRepository.findById(user_id) == null){
            System.out.println("User does not exist");
            return false;
        }

        // Check if the group exists
        if(groupRepository.findById(group_id) == null){
            System.out.println("Group does not exist");
            return false;
        }

        return groupRepository.findById(group_id).get().getIsAdmin() == user_id;

    }

    /** Returns a list of users in the group with the specified id */
    public List<UserDTO> ListUserInGroup(Long group_id) {
        List<ApiUser> users = groupRepository.findById(group_id).get().getUserList();
        List<UserDTO> usersDTOs = new ArrayList<>();
        for(ApiUser user : users){
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            usersDTOs.add(userDTO);
        }
        return usersDTOs;
    }

    /** Returns the group with the specified id */
    public Group deleteFromGroup(Long groupId) {
        try {
            // Delete the users from the group
            Group group = groupRepository.findById(groupId).get();
            for(ApiUser user : group.getUserList()){
                userRepository.delete(user);
            }

            for (Car car : group.getCarList()) {
                carRepository.delete(car);
            }

            return groupRepository.findById(groupId).get();
        } catch (Exception e) {
            throw new RuntimeException("Group not found");
        }
    }

    public Group getGroupById(Long groupId) {

    return groupRepository.findById(groupId).get();
    }
}
