package com.cheku.cheku.service;

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

    //Done
    public Pneus addPneus(Pneus pneus){
        return pneusRepository.save(pneus);
    }
}
