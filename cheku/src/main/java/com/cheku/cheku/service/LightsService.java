package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LightsService {
    @Autowired
    private LuzesRepository luzesRepository;

    public List<LightsHistory> getAllLuzes() {
        return luzesRepository.findAll();
    }

    public LightsHistory getLastLightState(Long car_id) {
        return luzesRepository.getLast(car_id);
    }

    public LightsHistory save(LightsHistory luzes) {
        try {
            return luzesRepository.save(luzes);
        } catch (Exception e) {
            System.out.println("Error saving luzes");
            return null;
        }
    }
}
