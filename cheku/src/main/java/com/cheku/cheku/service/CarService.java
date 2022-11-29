package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).get();
    }

    public Car addCar(Car car){
        return carRepository.save(car);
    }
}
