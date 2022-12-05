package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OleoService {

    @Autowired
    private OleoRepository oleoRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Oleo> getAllOleos() {
        return oleoRepository.findAll();
    }

    public Oleo save(Oleo oleo) {
        try{
            Car car  = carRepository.findById(oleo.getCar().getId()).get();
            oleo.setCar(car);
        }
        catch (Exception e){
            throw new RuntimeException("Car not found");
        }
        try {
            return oleoRepository.save(oleo);
        } catch (Exception e) {
           throw new RuntimeException("Error saving oil");
        }
    }

}