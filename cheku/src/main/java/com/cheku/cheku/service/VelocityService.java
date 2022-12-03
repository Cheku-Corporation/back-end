package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheku.cheku.auxiliar_classes.Velocity;
import com.cheku.cheku.exception.ResourceNotFoundException;

import java.util.ArrayList;
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

    public List<Velocity> getLast100Velocities(Long car_id) {
        return velocityRepository.getLast100byCarId(car_id); 
    }

    public List<Velocity> getLast1000Velocities(Long car_id) {
        return velocityRepository.getLast1000byCarId(car_id); 
    }

    public HistoryVelocity addVelocity(HistoryVelocity velocity) throws ResourceNotFoundException {
        // if (carRepository.existsById(velocity.getCar().getId())){
        //     System.out.println("Carro existe");
        //     Car car = carRepository.findById(velocity.getCar().getId()).orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + velocity.getCar().getId()));
        //     velocity.setCar(car);
        // }
        return velocityRepository.save(velocity);
    }
}
