package com.cheku.cheku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cheku.cheku.model.*;

/**
 * The CarRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the car table in the database.
 */
@Repository
public interface MotorRepository extends JpaRepository<Motor, Long>  {
    public Motor findByPowerAndDisplacementAndModel(int power, int displacement, String model);
}
