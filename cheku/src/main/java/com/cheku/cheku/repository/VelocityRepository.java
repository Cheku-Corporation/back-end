package com.cheku.cheku.repository;

import com.cheku.cheku.auxiliar_classes.Velocity;
import com.cheku.cheku.model.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface VelocityRepository extends JpaRepository<HistoryVelocity, Long> {
    
    @Query(value = "SELECT velocity, gear, date FROM historyVelocity LIMIT 100", nativeQuery = true)
    List<Velocity> getLast100byCarId(Long car_id);

    @Query(value = "SELECT velocity, gear, date FROM historyVelocity LIMIT 1000", nativeQuery = true)
    List<Velocity> getLast1000byCarId(Long car_id);
}
