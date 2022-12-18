package com.cheku.cheku.repository;

import com.cheku.cheku.model.LightsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LuzesRepository extends JpaRepository<LightsHistory, Long> {

    @Query(value = "SELECT * FROM lights ORDER BY date DESC LIMIT 1", nativeQuery = true)
    LightsHistory getLast(Long car_id);
}
