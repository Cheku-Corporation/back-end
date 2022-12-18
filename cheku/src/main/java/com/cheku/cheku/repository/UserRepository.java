package com.cheku.cheku.repository;



import com.cheku.cheku.model.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApiUser, Long> {
    Optional<ApiUser> findByEmail(String email);

    void deleteByEmail(String email);

}