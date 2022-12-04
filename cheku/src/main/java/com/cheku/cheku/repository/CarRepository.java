package com.cheku.cheku.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.*;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>  {
    public Car findByMatricula(String matricula);
}
