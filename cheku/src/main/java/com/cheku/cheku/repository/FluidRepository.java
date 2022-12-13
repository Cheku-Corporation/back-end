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
}
