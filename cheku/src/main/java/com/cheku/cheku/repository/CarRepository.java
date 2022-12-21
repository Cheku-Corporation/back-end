package com.cheku.cheku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.*;

/**
 * The CarRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the car table in the database.
 */

@Repository
public interface CarRepository extends JpaRepository<Car, Long>  {

/**
 * Checks if a car with a certain registration number exists. */
    boolean existsByMatricula(String matricula);

}
