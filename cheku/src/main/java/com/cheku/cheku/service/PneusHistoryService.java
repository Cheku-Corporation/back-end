package com.cheku.cheku.service;


import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PneusHistoryService {

    @Autowired
    private PneusHistoryRepository pneusHistoryRepository;

    @Autowired
    private CarRepository carRepository;

    public List<PneusHistory> getAllPneusHistory() {
        return pneusHistoryRepository.findAll();
    }

    public PneusHistory savePneusHistory(PneusHistory pneusHistory) {
        try{
            Car car  = carRepository.findById(pneusHistory.getCar().getId()).get();
            pneusHistory.setCar(car);
        }
        catch (Exception e){
            System.out.println("Car not found");
            return null;
        }
        try {
            return pneusHistoryRepository.save(pneusHistory);
        } catch (Exception e) {
            System.out.println("Error saving pneusHistory");
            return null;
        }
    }
}
