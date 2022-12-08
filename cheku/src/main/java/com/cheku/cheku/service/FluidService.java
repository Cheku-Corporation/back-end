package com.cheku.cheku.service;

import com.cheku.cheku.model.Fluid;
import com.cheku.cheku.repository.FluidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FluidService {

    @Autowired
    private FluidRepository fluidRepository;

    @Autowired
    private CarService carService;

    public Fluid addFluid(Fluid fluid) {
        try {
            carService.getCar(fluid.getCar().getId());
            fluid.setCar(carService.getCar(fluid.getCar().getId()));
        } catch (Exception e) {
            System.out.println("Car not found");
            return null;
        }
        try{
            return fluidRepository.save(fluid);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Fluid> getAllFluids() {
        return fluidRepository.findAll();
    }

    public Fluid getFluid(Long id) {
        return fluidRepository.findById(id).get();
    }

}
