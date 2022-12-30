package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.Coordinates;
import com.cheku.cheku.model.Localization;
import com.cheku.cheku.repository.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocalizationService {

    @Autowired
    private LocalizationRepository localizationRepository;

    @Autowired
    private TripService tripService;

    public List<Localization> getAll() {
        return localizationRepository.findAll();
    }

    public Localization addLocalization(Localization localization) {
        try {
            return localizationRepository.save(localization);
        } catch (Exception e) {
            System.out.println("Error saving localization");
            return null;
        }
    }

    public Coordinates getLast(Long car_id) {
        Long trip_id = tripService.getCarCurrentTrip(car_id).getId();
        Localization l = localizationRepository.getLast(trip_id);
        return new Coordinates(l.getLatitude(), l.getLongitude());
    }
}
