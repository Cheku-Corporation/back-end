package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.VelocityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VelocityService {

    @Autowired
    private VelocityRepository velocityRepository;

    public List<HistoryVelocity> getAllVelocity() {
        return velocityRepository.findAll();
    }

    public HistoryVelocity getVelocityById(Long id) {
        return velocityRepository.findById(id).get();
    }

    public void save(HistoryVelocity velocity) {
        velocityRepository.save(velocity);
    }

    public void update(HistoryVelocity velocity) {
        velocityRepository.save(velocity);
    }

    public void delete(Long id) {
        velocityRepository.deleteById(id);
    }
}
