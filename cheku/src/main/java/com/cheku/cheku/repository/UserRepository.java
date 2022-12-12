package com.cheku.cheku.repository;


import com.cheku.cheku.auxiliar_classes.ProcessedUser;
import com.cheku.cheku.model.ApiUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApiUser, Long> {
    Optional<ApiUser> findByEmail(String email);

    @Query(value = "SELECT name, email FROM users", nativeQuery = true)
    List<ProcessedUser> getAllbyNameEmail();

    @Query(value = "SELECT name, email FROM users where email = email and name = name LIMIT 1", nativeQuery = true)
    ProcessedUser getUserbyNameEmail(String email, String name);

    long findIdByEmail(String email);
}