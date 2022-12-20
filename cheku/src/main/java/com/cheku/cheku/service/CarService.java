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
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
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

    public Car updateCar(Car car, Car carUpdate, long userId) throws ResourceNotFoundException {
        //verificar se o carro existe
        if (carRepository.findById(car.getId()) == null) {
            throw new ResourceNotFoundException("The car doesn't exist");
        }
        //verificar se o grupo existe
        if (groupRepository.findById(carUpdate.getGroup().getId()) == null) {
            throw new ResourceNotFoundException("The group doesn't exist");
        }

        //verificar se o model existe
        if (carModelRepository.findByModel(carUpdate.getCarModel().getModel()) == null) {
            throw new ResourceNotFoundException("The car model doesn't exist");
        }

        //verificar se o carro pertence ao grupo
        if (car.getGroup().getId() != carUpdate.getGroup().getId()) {
            throw new ResourceNotFoundException("The car doesn't belong to the group");
        }

        car.setMatricula(carUpdate.getMatricula());
        car.setCarModel(carUpdate.getCarModel());
        car.setGroup(carUpdate.getGroup());
        car.setInsuranceDate(carUpdate.getInsuranceDate());
        car.setInspectionDate(carUpdate.getInspectionDate());

        return carRepository.save(car);
    }

    public Car getCarById(Long car_id) {
        try {
            return carRepository.findById(car_id).get();
        } catch (Exception e) {
            throw new RuntimeException("Car not found");
        }
    }
}
