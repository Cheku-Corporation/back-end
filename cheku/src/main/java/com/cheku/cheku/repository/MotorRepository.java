package com.cheku.cheku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.*;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long>  {

}