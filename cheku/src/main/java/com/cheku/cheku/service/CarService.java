package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CarService {
    // Repositories for accessing data in the database
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private NotificationService notificationService;

    /** Returns a list of all cars in the database */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /** Adds a new car to the database
     * @param car the car to be added
     * @throws ResourceNotFoundException if the car model or group does not exist
     * @return the added car
     */
    public Car addCar(Car car,long userId)  {

        // Check if the group exists
        if (groupRepository.findById(car.getGroup().getId()) == null) {
            throw new RuntimeException("The group doesn't exist");
        }

        // Check if the user is the group admin
        long adminId = groupRepository.findById(car.getGroup().getId()).get().getIsAdmin();
        if (adminId != userId) {
            throw new RuntimeException("User is not admin");
        }

        // Check if the car model exists
        if (carModelRepository.findByModel(car.getCarModel().getModel()) == null) {
            throw new RuntimeException("The car model doesn't exist");
        }

        notificationService.checkInsuranceDate(car, car.getGroup().getId());

        try{
            // Set the group and car model for the car
            car.setGroup(groupRepository.findById(car.getGroup().getId()).get());
            car.setCarModel(carModelRepository.findByModel(car.getCarModel().getModel()));
            // Save the car to the database
            carRepository.save(car);
            // Add the car to the group
            Group group = groupRepository.findById(car.getGroup().getId()).get();
            group.addCar(car);
            groupRepository.save(group);
            return carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException("Error saving car");
        }
    }


    /** Returns the car with the given ID
     * @param id the ID of the car
     * @throws ResourceNotFoundException if the car does not exist
     * @return the car with the given ID
     */
    public Car getCar(Long id) throws ResourceNotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
    }

    /** Returns true if a car with the specified id exists in the database, false otherwise*/
    public Boolean existsById(Long id) {
        return carRepository.existsById(id);
    }

    /** Returns true if a car with the specified registration number (matricula) exists in the database, false otherwise*/
    public boolean existsByMatricula(String matricula) {
        return carRepository.existsByMatricula(matricula);
    }


    /** Deletes the car with the given ID
     * @param id the ID of the car
     * @throws ResourceNotFoundException if the car does not exist
     */
    public Car deleteCar(Long car_id) {
        Car car = carRepository.findById(car_id).get();
        Group group = groupRepository.findById(car.getGroup().getId()).get();
        try {
            group.removeCar(car);
            carRepository.delete(car);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting car");
        }
        return car;
    }

    /** Updates the car with the given ID
     * @param id the ID of the car
     * @param car the new car
     * @throws ResourceNotFoundException if the car does not exist
     * @return the updated car
     */
    public Car updateCar(Car car, Car carUpdate, long userId) throws ResourceNotFoundException {
        // Check if the car exists
        if (carRepository.findById(car.getId()) == null) {
            throw new ResourceNotFoundException("The car doesn't exist");
        }
        // Check if the group exists
        if (groupRepository.findById(carUpdate.getGroup().getId()) == null) {
            throw new ResourceNotFoundException("The group doesn't exist");
        }

        if (carUpdate.getCarModel() != null) {
                // Check if the car model exists
            if (carModelRepository.findByModel(carUpdate.getCarModel().getModel()) == null) {
                throw new ResourceNotFoundException("The car model doesn't exist");
            }
            car.setCarModel(carModelRepository.findByModel(carUpdate.getCarModel().getModel()));
        }

        if(carUpdate.getGroup() != null) {
            // Check if the car belongs to the group
            if (car.getGroup().getId() != carUpdate.getGroup().getId()) {
                throw new ResourceNotFoundException("The car doesn't belong to the group");
            }
            car.setGroup(groupRepository.findById(carUpdate.getGroup().getId()).get());
        }

        // Update the car details

        if(carUpdate.getMatricula() != null){
            car.setMatricula(carUpdate.getMatricula());
        }
        if(carUpdate.getInsuranceDate() != null){
            car.setInsuranceDate(carUpdate.getInsuranceDate());
        }
        if(carUpdate.getInspectionDate() != null){
            car.setInspectionDate(carUpdate.getInspectionDate());
        }

        notificationService.checkInsuranceDate(car, car.getGroup().getId());
        return carRepository.save(car);
    }
}
