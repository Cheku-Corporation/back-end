package com.cheku.cheku.repository;


import com.cheku.cheku.model.Tires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiresRepository extends JpaRepository<Tires, Long>  {
    public Tires findByBrandAndModel(String brand, String model);
}
