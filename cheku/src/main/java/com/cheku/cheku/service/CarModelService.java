package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.model.dto.CarModelDTO;
import com.cheku.cheku.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarModelService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    private TiresRepository pneusRepository;

    /**
     * Returns a list of all car models as CarModelDTO objects
     * @return a list of CarModelDTO objects
     */
    public List<CarModelDTO> getAllCarModels() {
        List<CarModel> cars = carModelRepository.findAll();
        List<CarModelDTO> carModels = new ArrayList<>();
        for (CarModel car : cars) {
            carModels.add(modelMapper.map(car, CarModelDTO.class));
        }
        return carModels;
    }

    /**
     * Creates a new car model
     * @param car the car model to be created
     * @return the created car model
     * @throws ResourceNotFoundException if a car model with the same brand, model, year, tank capacity, type, motor, and tires already exists, or if the motor or tires do not exist
     */
    public CarModel createCarModel(CarModel car) throws ResourceNotFoundException {
        if (carModelRepository.findByBrandAndModelAndYearAndTankCapacityAndTypeAndMotorAndTires(car.getBrand(), car.getModel(), car.getYear(), car.getTankCapacity(), car.getType(), car.getMotor(), car.getTires()) != null) {
            throw new ResourceNotFoundException("The Model car already exists");
        }
        // Check if the motor exists
        else if (motorRepository.findById(car.getMotor().getId()) == null) {
                throw new ResourceNotFoundException("The motor doesn't exist");
            }
            // Check if the tires exist
            else if (pneusRepository.findById(car.getTires().getId()) == null) {
                throw new ResourceNotFoundException("The pneus doesn't exist");
            }
            try{
                // Find the motor and tires by their ID
                car.setMotor(motorRepository.findById(car.getMotor().getId()).get());
                car.setTires(pneusRepository.findById(car.getTires().getId()).get());
                // Save the car model and return it
                return carModelRepository.save(car);
            } catch (Exception e) {
            // If an error occurs while saving the car model, throw a ResourceNotFoundException
            throw new ResourceNotFoundException("Error saving car");
            }
    }
}