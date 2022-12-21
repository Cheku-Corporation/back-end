package com.cheku.cheku.repository;

import com.cheku.cheku.auxiliar_classes.SimpleFluid;
import com.cheku.cheku.model.Fluid;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The FluidRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the fluid table in the database.
 */

@Repository
public interface FluidRepository extends JpaRepository<Fluid, Long> {

    /** Gets all the fluids from the fluid table as a list of SimpleFluid objects. */
    @Query(value = "SELECT oil, water, fuel FROM fluid", nativeQuery = true)
    List<SimpleFluid> getAll();

    @Query(value = "SELECT * FROM fluid WHERE trip_id = ?1 ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Fluid getLast(Long trip_id);
}
