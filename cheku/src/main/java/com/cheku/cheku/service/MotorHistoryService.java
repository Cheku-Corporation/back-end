package com.cheku.cheku.service;

import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.MotorHistory;
import com.cheku.cheku.repository.CarRepository;
import com.cheku.cheku.repository.MotorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MotorHistoryService {
    @Autowired
    private MotorHistoryRepository motorHistoryRepository;

    @Autowired
    private CarRepository carRepository;

    public MotorHistory saveMotorHistory(MotorHistory motorHistory) {
        try{
            Car car  = carRepository.findById(motorHistory.getCar().getId()).get();
            motorHistory.setCar(car);
        }
        catch (Exception e){
            System.out.println("Car not found");
            return null;
        }
        try {
            return motorHistoryRepository.save(motorHistory);
        } catch (Exception e) {
            System.out.println("Error saving motorHistory: " + e.getMessage());
            return null;
        }
    }

    public MotorHistory getMotorHistoryById(Long id) {
        return motorHistoryRepository.findById(id).orElse(null);
    }

    public List<MotorHistory> getAllMotorHistory() {
        return motorHistoryRepository.findAll();
    }

}
