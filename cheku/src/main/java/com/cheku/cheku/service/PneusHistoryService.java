package com.cheku.cheku.service;


import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PneusHistoryService {

    @Autowired
    private PneusHistoryRepository pneusHistoryRepository;

    public List<PneusHistory> getAllPneusHistory() {
        return pneusHistoryRepository.findAll();
    }

    public PneusHistory getLastTireState(Long car_id) {
        return pneusHistoryRepository.getLast(car_id);
    }

    public PneusHistory savePneusHistory(PneusHistory pneusHistory) {
        try {
            return pneusHistoryRepository.save(pneusHistory);
        } catch (Exception e) {
            System.out.println("Error saving pneusHistory");
            e.printStackTrace();
            return null;
        }
    }
}
