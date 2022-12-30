package com.cheku.cheku.repository;

import com.cheku.cheku.model.LightsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The LuzesRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the luzes table in the database.
 */
@Repository
public interface LightsRepository extends JpaRepository<LightsHistory, Long> {

    @Query(value = "SELECT * FROM lights_history ORDER BY date DESC LIMIT 1", nativeQuery = true)
    LightsHistory getLast(Long car_id);
}
