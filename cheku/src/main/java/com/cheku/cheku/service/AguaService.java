package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AguaService {

    @Autowired
    private AguaRepository aguaRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Agua> getAllAguas() {
        return aguaRepository.findAll();
    }

    public Agua save(Agua agua) {
        if(agua.getLiters() == null || agua.getLiters() < 0 || agua.getLiters() > 1){
            System.out.println("Invalid Liters");
            return null;
        }
        try{
            Car car  = carRepository.findById(agua.getCar().getId()).get();
            agua.setCar(car);
        }
        catch (Exception e){
            System.out.println("Car not found");
            return null;
        }
        try {
            return aguaRepository.save(agua);
        } catch (Exception e) {
            System.out.println("Error saving agua");
            return null;
        }
    }
}
