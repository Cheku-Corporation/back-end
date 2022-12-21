package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Tires;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiresService {

    @Autowired
    private TiresRepository tiresRepository;

    /** Returns a list of all tires in the database */
    public List<Tires> getAllTires() {
        return tiresRepository.findAll();
    }


    public Tires getTires(Long id) {
        return tiresRepository.findById(id).get();
    }

    /** Creates a new tires in the database
     * @param tires The tires to be created
     * @return The newly created tires
     */
    public Tires addTires(Tires tires) throws ResourceNotFoundException {
        // Check if a tires with the same brand and Model already exists
        if (tiresRepository.findByBrandAndModel(tires.getBrand(), tires.getModel()) != null) {
            throw new ResourceNotFoundException("Pneu already exists");
        }
        try {
            return tiresRepository.save(tires);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving pneu");
        }
    }

    /** Updates a tires in the database
     * @param id The id of the tires to be updated
     * @param tires The tires to be updated
     * @return The updated tires
     */
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

}
