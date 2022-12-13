package com.cheku.cheku.repository;

import com.cheku.cheku.model.*;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface TripRepository extends JpaRepository<Trip, Long> {
    
    @Query(value = "SELECT number FROM trip WHERE car = ?1 ORDER BY number DESC LIMIT 1", nativeQuery = true)
    Long getNumbersByCar(Long car_id);

    @Query(value = "SELECT * FROM trip WHERE car = ?1 ORDER BY number DESC LIMIT 1", nativeQuery = true)
    Trip getCurrentCarTrip(Long car_id);

    @Query(value = "SELECT * FROM trip WHERE car = ?1 AND number = ?2 LIMIT 1", nativeQuery = true)
    Trip getTripWithCarAndNumber(Long car_id, Long number);

    @Modifying
    @Transactional
    @Query(value = "UPDATE trip SET distance = ?2 WHERE id = ?1", nativeQuery = true)
    void changeTripDistanceTraveled(Long car_id, Double distance);
}
