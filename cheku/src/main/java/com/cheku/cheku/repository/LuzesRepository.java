package com.cheku.cheku.repository;

import com.cheku.cheku.model.LightsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuzesRepository extends JpaRepository<LightsHistory, Long> {
}
