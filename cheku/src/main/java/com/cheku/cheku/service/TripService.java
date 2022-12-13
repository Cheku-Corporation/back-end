package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheku.cheku.auxiliar_classes.Velocity;
import com.cheku.cheku.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CarRepository carRepository;

    public Trip addTrip(Trip trip) throws ResourceNotFoundException {
        return tripRepository.save(trip);
    }

    public void changeDistance(Long trip_id, Double newDistance) {
        tripRepository.changeTripDistanceTraveled(trip_id, newDistance);
    }

    public Long getCarCurrentTripNumber(Long car_id) {
        return tripRepository.getNumbersByCar(car_id);
    }

    public Trip getCarCurrentTrip(Long car_id) {
        return tripRepository.getCurrentCarTrip(car_id);
    }

    public Trip getTripWithCarAndNumber(Long car_id, Long number) throws ResourceNotFoundException {
        return tripRepository.getTripWithCarAndNumber(car_id, number);
    }

    public List<Trip> getAll() {
        return tripRepository.findAll(); 
    }

    // public List<Velocity> getLast1000Velocities(Long car_id) {
    //     return velocityRepository.getLast1000byCarId(car_id); 
    // }

    // public SpeedHistory addVelocity(SpeedHistory velocity) throws ResourceNotFoundException {
    //     return velocityRepository.save(velocity);
    // }
}