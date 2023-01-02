package com.cheku.cheku.service;

import com.cheku.cheku.data_representation.Coordinates;
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
        //System.out.println("trip id: " + trip_id);
        Localization l = localizationRepository.getLast(trip_id).get(0);
        //System.out.println("location: " + l.getId());
        return new Coordinates(l.getLatitude(), l.getLongitude());
    }
}
