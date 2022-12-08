package com.cheku.cheku.repository;

import com.cheku.cheku.model.MotorHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorHistoryRepository extends JpaRepository<MotorHistory, Long> {
}

