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
    private MotorRepository motorRepository;

    @Autowired
    private PneusRepository pneusRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) throws ResourceNotFoundException {
        // verificar se não existe um carro com a mesma matricula
        if (carRepository.findByMatricula(car.getMatricula()) != null) {
            System.out.println("Car already exists");
            throw new ResourceNotFoundException("Car already exists");
        }
        // verificar se o grupo existe
        else if (groupRepository.findById(car.getGroup().getId()) == null) {
            System.out.println("The group doesn't exist");
            throw new ResourceNotFoundException("The group doesn't exist");
        }
        // verificar se model existe
        else if (carModelRepository.findById(car.getModel().getId()) == null) {
            System.out.println("The car model doesn't exist");
            throw new ResourceNotFoundException("The car model doesn't exist");
        }

        try {
            car.setModel();
            return carRepository.save(car);
        }catch (Exception e){
            throw new ResourceNotFoundException("The car model doesn't exist");
        }
    }

    public Car getCar(Long id) throws ResourceNotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
    }

    public Boolean existsById(Long id) {
        return carRepository.existsById(id);
    }



}
