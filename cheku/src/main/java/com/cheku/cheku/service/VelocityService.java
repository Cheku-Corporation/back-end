package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cheku.cheku.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class VelocityService {

    @Autowired
    private VelocityRepository velocityRepository;

    @Autowired
    private CarRepository carRepository;

    public List<HistoryVelocity> getAllVelocity() {
        return velocityRepository.findAll();
    }

    public HistoryVelocity addVelocity(HistoryVelocity velocity) throws ResourceNotFoundException {
        if (carRepository.existsById(velocity.getCar().getId())){
            System.out.println("Carro existe");
            Car car = carRepository.findById(velocity.getCar().getId()).orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + velocity.getCar().getId()));
            velocity.setCar(car);
        }
        return velocityRepository.save(velocity);
    }
}
