package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.service.GroupService;
import com.cheku.cheku.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.service.CarService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class APIUpdateController {

    @Autowired
    private CarService carService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @PutMapping("user/group/car/{id}")
    public Car updateCar(@PathVariable Long id, @Valid @RequestBody String data) throws ResourceNotFoundException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Car car = carService.getCarById(id);

        Car carUpdate = mapper.readValue(data, Car.class);

        Long groupId = mapper.readTree(data).get("group").get("id").asLong();
        Long userId = mapper.readTree(data).get("userId").asLong();

        if (groupService.getGroupById(groupId).getIsAdmin() != userId){
            throw new ResourceNotFoundException("Not Authorized");
        }

        if (carService.existsByMatricula(carUpdate.getMatricula())){
            throw new ResourceNotFoundException("Matricula already exists");
        }

        return carService.updateCar(car,carUpdate, userId);
    }

}
