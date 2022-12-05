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
        try{
            Car car  = carRepository.findById(agua.getCar().getId()).get();
            agua.setCar(car);
        }
        catch (Exception e){
            throw new RuntimeException("Car not found");
        }
        try {
            return aguaRepository.save(agua);
        } catch (Exception e) {
           throw new RuntimeException("Error saving agua");
        }
    }
}
