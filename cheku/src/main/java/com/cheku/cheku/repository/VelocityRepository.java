package com.cheku.cheku.repository;

import com.cheku.cheku.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VelocityRepository extends JpaRepository<HistoryVelocity, Long> {

}
