package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.CarRepository;
import com.cheku.cheku.repository.CombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CombustivelService {

    @Autowired
    private CombustivelRepository combustivelRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Combustivel> getAllCombustiveis() {
        return combustivelRepository.findAll();
    }

    public Combustivel addCombustivel(Combustivel combustivel) {
        try{
            Car car  = carRepository.findById(combustivel.getCar().getId()).get();
            combustivel.setCar(car);
        }
        catch (Exception e){
            throw new RuntimeException("Car not found");
        }
        try {
            return combustivelRepository.save(combustivel);
        } catch (Exception e) {
            throw new RuntimeException("Error saving combustivel");
        }
    }
}
