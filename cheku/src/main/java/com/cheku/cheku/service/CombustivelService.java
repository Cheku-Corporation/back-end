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
        if(combustivel.getLiters() == null || combustivel.getLiters() < 0 || combustivel.getLiters() > 1){
            System.out.println("Invalid Litter");
            return null;
        }
        try{
            Car car  = carRepository.findById(combustivel.getCar().getId()).get();
            combustivel.setCar(car);
            Double liters = car.getCapacidadeDeposito() * combustivel.getPercentagem();
            combustivel.setLiters(liters);
        }
        catch (Exception e){
            System.out.println("Car not found");
            return null;
        }
        try {
            return combustivelRepository.save(combustivel);
        } catch (Exception e) {
            System.out.println("Error saving combustivel");
            return null;
        }
    }
}
