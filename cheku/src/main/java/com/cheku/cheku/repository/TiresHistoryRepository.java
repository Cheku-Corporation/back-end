package com.cheku.cheku.repository;

import com.cheku.cheku.model.TiresHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TiresHistoryRepository extends JpaRepository<TiresHistory, Long> {

    @Query(value = "SELECT * FROM tires_history ORDER BY date DESC LIMIT 1", nativeQuery = true)
    TiresHistory getLast(Long car_id);
}

