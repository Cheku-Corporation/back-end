package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LuzesService {
    @Autowired
    private LuzesRepository luzesRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Luzes> getAllLuzes() {
        return luzesRepository.findAll();
    }

    public Luzes save(Luzes luzes) {
        try{
            Car car  = carRepository.findById(luzes.getCar().getId()).get();
            luzes.setCar(car);
        }
        catch (Exception e){
            System.out.println("Car not found");
            return null;
        }
        try {
            return luzesRepository.save(luzes);
        } catch (Exception e) {
            System.out.println("Error saving luzes");
            return null;
        }
    }
}
