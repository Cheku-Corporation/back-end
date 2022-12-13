package com.cheku.cheku.service;

import com.cheku.cheku.model.Localization;
import com.cheku.cheku.repository.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocalizationService {

    @Autowired
    private LocalizationRepository localizationRepository;

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

}
