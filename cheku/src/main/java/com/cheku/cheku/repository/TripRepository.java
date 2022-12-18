package com.cheku.cheku.repository;

import com.cheku.cheku.model.*;

import java.util.Date;
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

    @Query(value = "SELECT * FROM trip WHERE car = ?1 AND end_date IS NOT NULL ORDER BY end_date DESC", nativeQuery = true)
    List<Trip> findAllPassed(Long car_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE trip SET distance = ?2 WHERE id = ?1", nativeQuery = true)
    void changeTripDistanceTraveled(Long car_id, Double distance);

    @Modifying
    @Transactional
    @Query(value = "UPDATE trip SET end_date = ?2, end_lat = ?3, end_long = ?4 WHERE id = ?1", nativeQuery = true)
    void finishTrip(Long car_id, Date timestamp, Double latitude, Double Longitude);
}
