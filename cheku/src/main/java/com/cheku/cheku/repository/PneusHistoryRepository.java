package com.cheku.cheku.repository;

import com.cheku.cheku.model.PneusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PneusHistoryRepository extends JpaRepository<PneusHistory, Long> {
}

