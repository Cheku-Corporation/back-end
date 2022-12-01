package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.CarRepository;
import com.cheku.cheku.repository.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    //Done
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    //DONE
    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).get();
    }
}
