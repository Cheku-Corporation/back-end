package com.cheku.cheku.repository;


import com.cheku.cheku.auxiliar_classes.ProcessedUser;
import com.cheku.cheku.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "SELECT name, email FROM users", nativeQuery = true)
    List<ProcessedUser> getAllbyNameEmail();
}