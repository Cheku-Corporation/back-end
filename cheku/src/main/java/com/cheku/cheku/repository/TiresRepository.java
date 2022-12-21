package com.cheku.cheku.repository;


import com.cheku.cheku.model.Tires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The TiresRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the tires table in the database.
 */
@Repository
public interface TiresRepository extends JpaRepository<Tires, Long>  {

    /** Finds a tires by brand, model */
    public Tires findByBrandAndModel(String brand, String model);
}
