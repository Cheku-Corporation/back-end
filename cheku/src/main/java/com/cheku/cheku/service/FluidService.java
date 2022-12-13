package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.SimpleFluid;
import com.cheku.cheku.model.Fluid;
import com.cheku.cheku.repository.FluidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FluidService {

    @Autowired
    private FluidRepository fluidRepository;

    public Fluid addFluid(Fluid fluid) {
        try{
            return fluidRepository.save(fluid);
        } catch (Exception e) {
            return null;
        }
    }

    public List<SimpleFluid> getAllFluids() {
        return fluidRepository.getAll();
    }

    public Fluid getFluid(Long id) {
        return fluidRepository.findById(id).get();
    }

}
