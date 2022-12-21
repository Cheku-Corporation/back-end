package com.cheku.cheku.repository;

import com.cheku.cheku.model.CarModel;
import com.cheku.cheku.model.Motor;
import com.cheku.cheku.model.Tires;
import com.cheku.cheku.model.enums.TypeCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The CarModelRepository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the car_model table in the database.
 */

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    /** Finds a car model by its model.*/
    CarModel findByModel(String model);

    /**Finds a car model by its brand, model, year, tank capacity, type, motor, and tires.*/
    CarModel findByBrandAndModelAndYearAndTankCapacityAndTypeAndMotorAndTires(String brand, String model, int year, Double tankCapacity, TypeCar type, Motor motor, Tires pneus);
}
