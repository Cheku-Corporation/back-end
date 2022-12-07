package com.cheku.cheku.repository;

import com.cheku.cheku.model.Fluid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FluidRepository extends JpaRepository<Fluid, Long> {
}
