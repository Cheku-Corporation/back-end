package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) throws ResourceNotFoundException {

        // verificar se o grupo existe
        if (groupRepository.findById(car.getGroup().getId()) == null) {
            throw new ResourceNotFoundException("The group doesn't exist");
        }
        // verificar se model existe
        if (carModelRepository.findByModel(car.getModel().getModel()) == null) {
            throw new ResourceNotFoundException("The car model doesn't exist");
        }
        try{
            car.setGroup(groupRepository.findById(car.getGroup().getId()).get());
            car.setModel(carModelRepository.findByModel(car.getModel().getModel()));
            return carRepository.save(car);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving car");
        }
    }

    public Car getCar(Long id) throws ResourceNotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
    }

    public Boolean existsById(Long id) {
        return carRepository.existsById(id);
    }

    public boolean existsByMatricula(String matricula) {
        return carRepository.existsByMatricula(matricula);
    }
}
