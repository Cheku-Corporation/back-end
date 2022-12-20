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

    public Car addCar(Car car,long userId)  {

        // verificar se o grupo existe
        if (groupRepository.findById(car.getGroup().getId()) == null) {
            throw new RuntimeException("The group doesn't exist");
        }
        Long adminId = groupRepository.findById(car.getGroup().getId()).get().getAdmin();
        if (adminId != userId) {
            throw new RuntimeException("User is not admin");
        }
        // verificar se model existe
        if (carModelRepository.findByModel(car.getCarModel().getModel()) == null) {
            throw new RuntimeException("The car model doesn't exist");
        }

        try{
            car.setGroup(groupRepository.findById(car.getGroup().getId()).get());
            car.setCarModel(carModelRepository.findByModel(car.getCarModel().getModel()));
            carRepository.save(car);
            //adicionar o carro ao grupo
            Group group = groupRepository.findById(car.getGroup().getId()).get();
            group.addCar(car);
            groupRepository.save(group);
            return carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException("Error saving car");
        }
    }

    public Car getCar(Long id) throws ResourceNotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public Boolean existsById(Long id) {
        return carRepository.existsById(id);
    }

    public boolean existsByMatricula(String matricula) {
        return carRepository.existsByMatricula(matricula);
    }


    public Car deleteCar(Long car_id) {
        Car car = carRepository.findById(car_id).get();
        Group group = groupRepository.findById(car.getGroup().getId()).get();
        group.removeCar(car);
        carRepository.delete(car);
        return car;
    }

    public Car updateCar(Long car_id, Car car) {
        carRepository.findById(car_id).get();
        car.setCar(car);
        return carRepository.save(car);
    }

    public boolean verifyCar(Long car_id, Long group_id) {
        return carRepository.existsByIdAndGroup(car_id, group_id);
    }
}
