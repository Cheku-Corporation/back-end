package com.cheku.cheku.service;

import com.cheku.cheku.model.Group;
import org.modelmapper.ModelMapper;
import com.cheku.cheku.model.ApiUser;
import com.cheku.cheku.model.dto.UserDTO;
import com.cheku.cheku.model.request.UserCreateRequest;
import com.cheku.cheku.repository.UserRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private GroupService groupService;
    private final BCryptPasswordEncoder passwordEncoder;

    public ApiUser readUserByEmail (String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void createUser(UserCreateRequest userCreateRequest) {
        ApiUser user = new ApiUser();
        Optional<ApiUser> byEmail = userRepository.findByEmail(userCreateRequest.getEmail());
        if (byEmail.isPresent()) {
            throw new RuntimeException("Usuário já registrado. Por favor, use um nome de usuário diferente.");
        }
        user.setEmail(userCreateRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        user.setRole(userCreateRequest.getRole());
        user.setName(userCreateRequest.getName());
        userRepository.save(user);
    }

    //dados de user
    // retornar apenas nome e email, id, groupName, groupID
    public UserDTO getCurrentUser(String email) throws JsonProcessingException {
        ApiUser user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;

    }
    public List<UserDTO> getAllUsers() {
        List<ApiUser> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (ApiUser user : users) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    //getUserByEmail
    public Optional<ApiUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        ApiUser user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
        //verificar se o user é admin de algum grupo
        //se for, deletar o grupo
        if (user.getGroup().getIsAdmin() == user.getId()) {
            groupService.deleteGroup(user.getGroup().getId());
        }else{
            //se não for, remover o user do grupo
            Group group = user.getGroup();
            group.removeUser(user);
            // verificar se o grupo ficou vazio
            if (!group.getUserListSize()) {
                groupService.deleteGroup(group.getId());
            }
        }

    }

    public boolean findUserById(Long user_id) {
        return userRepository.existsById(user_id);
    }

    public Group ListGroupInUser(Long user_id) {
        return userRepository.findById(user_id).get().getGroup();
    }

    public ApiUser getUser(Long group_id) {
        return userRepository.findById(group_id).get();
    }
}
