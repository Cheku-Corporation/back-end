package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.service.GroupService;
import com.cheku.cheku.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.cheku.cheku.model.Car;
import com.cheku.cheku.service.CarService;

/**
 * Rest controller for handling requests to updates.
 */
@RestController
@RequestMapping("/api")
public class APIUpdateController {

    private final CarService carService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public APIUpdateController(CarService carService, GroupService groupService, UserService userService) {
        this.carService = carService;
        this.groupService = groupService;
        this.userService = userService;
    }
    /** Updates a car with the given data. */
    @PutMapping("user/group/car/{id}")
    public Car updateCar(@PathVariable Long id, @Valid @RequestBody String data) throws ResourceNotFoundException, JsonProcessingException {
        // Set up an object mapper to parse the data
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Get the current car and the updated car data
        Car car = carService.getCarById(id);
        Car carUpdate = mapper.readValue(data, Car.class);

        // Get the group ID and user ID from the data
        Long groupId = mapper.readTree(data).get("group").get("id").asLong();
        Long userId = mapper.readTree(data).get("userId").asLong();

        // Check if the user is authorized to update the car
        if (groupService.getGroupById(groupId).getIsAdmin() != userId) {
            throw new ResourceNotFoundException("Not Authorized");
        }

        // Check if the updated car's license plate already exists
        if (carService.existsByMatricula(carUpdate.getMatricula())) {
            throw new ResourceNotFoundException("Matricula already exists");
        }

        // Update the car and return the result
        return carService.updateCar(car, carUpdate, userId);
    }
}


