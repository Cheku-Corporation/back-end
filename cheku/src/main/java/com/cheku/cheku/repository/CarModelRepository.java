package com.cheku.cheku.repository;

import com.cheku.cheku.model.CarModel;
import com.cheku.cheku.model.Motor;
import com.cheku.cheku.model.Pneus;
import com.cheku.cheku.model.enums.TypeCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    CarModel findByModel(String model);

    CarModel findByBrandAndModelAndYearAndTankCapacityAndTypeAndMotorAndPneus(String brand, String model, int year, Double tankCapacity, TypeCar type, Motor motor, Pneus pneus);
}
