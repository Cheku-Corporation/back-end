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
        if(oleo.getLiters() == null || oleo.getLiters() < 0 || oleo.getLiters() > 100){
            System.out.println("Invalid Liters");
            return null;
        }
        try{
            Car car  = carRepository.findById(oleo.getCar().getId()).get();
            oleo.setCar(car);
        }
        catch (Exception e){
            System.out.println("Car not found");
            return null;
        }
        try {
            return oleoRepository.save(oleo);
        } catch (Exception e) {
            System.out.println("Error saving oil");
            return null;
        }
    }

}
