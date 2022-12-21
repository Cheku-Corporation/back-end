package com.cheku.cheku.repository;

import com.cheku.cheku.auxiliar_classes.Velocity;
import com.cheku.cheku.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The VelocityRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the velocity table in the database.
 */
public interface VelocityRepository extends JpaRepository<SpeedHistory, Long> {
    
    @Query(value = "SELECT velocity, gear, date FROM speed_history LIMIT 100", nativeQuery = true)
    List<Velocity> getLast100byCarId(Long car_id);

    @Query(value = "SELECT velocity, gear, date FROM speed_history LIMIT 1000", nativeQuery = true)
    List<Velocity> getLast1000byCarId(Long car_id);

    @Query(value = "SELECT * FROM speed_history ORDER BY date DESC LIMIT 1", nativeQuery = true)
    SpeedHistory getLast(Long car_id);
}
