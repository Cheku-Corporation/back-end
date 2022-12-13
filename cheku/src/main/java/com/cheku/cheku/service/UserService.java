package com.cheku.cheku.service;

import com.cheku.cheku.model.ApiUser;
import com.cheku.cheku.model.request.UserCreateRequest;
import com.cheku.cheku.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.cheku.cheku.auxiliar_classes.ProcessedUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
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

    public List<ProcessedUser> getAllUsers() {
        return userRepository.getAllbyNameEmail();
    }

    //getUserByEmail
    public Optional<ApiUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }
}
