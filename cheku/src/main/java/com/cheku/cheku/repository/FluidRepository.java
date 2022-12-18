package com.cheku.cheku.repository;

import com.cheku.cheku.auxiliar_classes.SimpleFluid;
import com.cheku.cheku.model.Fluid;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FluidRepository extends JpaRepository<Fluid, Long> {

    @Query(value = "SELECT oil, water, fuel FROM fluid", nativeQuery = true)
    List<SimpleFluid> getAll();

    @Query(value = "SELECT * FROM fluid WHERE trip_id = ?1 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Fluid getLast(Long trip_id);
}
