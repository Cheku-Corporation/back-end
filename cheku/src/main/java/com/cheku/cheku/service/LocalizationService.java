package com.cheku.cheku.service;

import com.cheku.cheku.model.Localization;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.repository.LocalizationRepository;
import com.cheku.cheku.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocalizationService {

    @Autowired
    private LocalizationRepository localizationRepository;

    @Autowired
    private CarRepository carRepository;


    public List<Localization> getAllLocalizations() {
        return localizationRepository.findAll();
    }

    public Localization addLocalization(Localization localization) {
        try{
            Car car  = carRepository.findById(localization.getCar().getId()).get();
            localization.setCar(car);
        }
        catch (Exception e){
            throw new RuntimeException("Car not found");
        }
        try {
            return localizationRepository.save(localization);
        } catch (Exception e) {
            throw new RuntimeException("Error saving localization");
        }
    }

}
