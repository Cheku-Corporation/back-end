package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    private CarRepository carRepository;

    public List<Car> getAllVelocity() {
        return carRepository.findAll();
    }

    public Car getVelocityById(Long id) {
        return carRepository.findById(id).get();
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public void update(Car car) {
        carRepository.save(car);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
