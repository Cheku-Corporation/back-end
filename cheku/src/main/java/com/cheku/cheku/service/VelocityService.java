package com.cheku.cheku.service;

import com.cheku.cheku.data_representation.Velocity;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VelocityService {

    @Autowired
    private VelocityRepository velocityRepository;

    public List<SpeedHistory> getAllVelocity() {
        return velocityRepository.findAll();
    }

    public SpeedHistory getLastVelocity(Long car_id) {
        return velocityRepository.getLast(car_id); 
    }

    public List<SpeedHistory> getVelocitiesByTrip(Long trip_id) {
        return velocityRepository.getByTrip(trip_id); 
    }

    public List<Velocity> getLast100Velocities(Long car_id) {
        return velocityRepository.getLast100byCarId(car_id); 
    }

    public List<Velocity> getLast1000Velocities(Long car_id) {
        return velocityRepository.getLast1000byCarId(car_id); 
    }

    public SpeedHistory addVelocity(SpeedHistory velocity) {
        try {
            return velocityRepository.save(velocity);
        } catch (Exception e) {
            System.out.println("Error saving Velocity");
            return null;
        }
    }
}
