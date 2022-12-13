package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    private PneusRepository pneusRepository;


     public List<String> getAllCarModels() {
          List<CarModel> cars = carModelRepository.findAll();
          List<String> carModels = new ArrayList<>();
            for (CarModel car : cars) {
                carModels.add(car.getModel());
            }
            return carModels;
     }


     public CarModel createCarModel(CarModel car) throws ResourceNotFoundException {
         //verificar se não existe um carModel com os mesmos dados
         if (carModelRepository.findByBrandAndModelAndYearAndTankCapacityAndTypeAndMotorAndPneus(car.getBrand(), car.getModel(), car.getYear(), car.getTankCapacity(), car.getType(), car.getMotor(), car.getPneus()) != null) {
           throw new ResourceNotFoundException("The Model car already exists");
         }
         //verificar se o motor existe
         else if (motorRepository.findById(car.getMotor().getId()) == null) {
           throw new ResourceNotFoundException("The motor doesn't exist");
         }
         //verificar se os pneus existem
         else if (pneusRepository.findById(car.getPneus().getId()) == null) {
           throw new ResourceNotFoundException("The pneus doesn't exist");
         }
         try{
             car.setMotor(motorRepository.findById(car.getMotor().getId()).get());
             car.setPneus(pneusRepository.findById(car.getPneus().getId()).get());
             return carModelRepository.save(car);
         } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving car");
         }
     }

}

