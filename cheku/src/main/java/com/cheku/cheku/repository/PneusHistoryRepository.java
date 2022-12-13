package com.cheku.cheku.repository;

import com.cheku.cheku.model.PneusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PneusHistoryRepository extends JpaRepository<PneusHistory, Long> {

    @Query(value = "SELECT * FROM tires_history ORDER BY date DESC LIMIT 1", nativeQuery = true)
    PneusHistory getLast(Long car_id);
}

