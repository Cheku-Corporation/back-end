package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Tires;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PneusService {

    @Autowired
    private TiresRepository tiresRepository;

    //Done
    public List<Tires> getAllTires() {
        return tiresRepository.findAll();
    }
    public Tires getTires(Long id) {
        return tiresRepository.findById(id).get();
    }

    public Tires addTires(Tires tires) throws ResourceNotFoundException {
        // verificar se não existe um pneu com o mesmo parâmetro
        if (tiresRepository.findByBrandAndModel(tires.getBrand(), tires.getModel()) != null) {
            throw new ResourceNotFoundException("Pneu already exists");
        }
        try {
            return tiresRepository.save(tires);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving pneu");
        }
    }

    public Tires updateTires(Long id, Tires tires) throws ResourceNotFoundException {
        Tires pneus1 = tiresRepository.findById(id).get();
        if (pneus1 == null) {
            throw new ResourceNotFoundException("Tires not found");
        }
        pneus1.setBrand(tires.getBrand());
        pneus1.setModel(tires.getModel());
        tiresRepository.save(pneus1);
        return pneus1;
    }

    public void deleteTires(Long id) throws ResourceNotFoundException {
        Tires tires = tiresRepository.findById(id).get();
        if (tires == null) {
            throw new ResourceNotFoundException("Tires not found");
        }
        tiresRepository.delete(tires);
    }

}
