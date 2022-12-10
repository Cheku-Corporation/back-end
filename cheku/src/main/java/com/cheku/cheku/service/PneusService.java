package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PneusService {

    @Autowired
    private PneusRepository pneusRepository;

    //Done
    public List<Pneus> getAllPneus() {
        return pneusRepository.findAll();
    }
    public Pneus getPneus(Long id) {
        return pneusRepository.findById(id).get();
    }

    public Pneus addPneus(Pneus pneus) throws ResourceNotFoundException {
        // verificar se não existe um pneu com o mesmo parâmetro
        if (pneusRepository.findByBrandAndModel(pneus.getBrand(), pneus.getModel()) != null) {
            throw new ResourceNotFoundException("Pneu already exists");
        }
        try {
            return pneusRepository.save(pneus);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving pneu");
        }
    }

    public Pneus updatePneus(Long id, Pneus pneus) throws ResourceNotFoundException {
        Pneus pneus1 = pneusRepository.findById(id).get();
        if (pneus1 == null) {
            throw new ResourceNotFoundException("Pneus not found");
        }
        pneus1.setBrand(pneus.getBrand());
        pneus1.setModel(pneus.getModel());
        pneusRepository.save(pneus1);
        return pneus1;
    }

    public void deletePneus(Long id) throws ResourceNotFoundException {
        Pneus pneus = pneusRepository.findById(id).get();
        if (pneus == null) {
            throw new ResourceNotFoundException("Pneus not found");
        }
        pneusRepository.delete(pneus);
    }
}
