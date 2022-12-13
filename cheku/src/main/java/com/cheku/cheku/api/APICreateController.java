package com.cheku.cheku.api;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.*;
import com.cheku.cheku.service.*;
import com.cheku.cheku.exception.ResourceNotFoundException;

import java.util.List;


@RestController
public class APICreateController {

    @Autowired
    private VelocityService velocityService;

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private PneusHistoryService pneusHistoryService;

    @Autowired
    private LightsService luzesService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CarService carService;

    //DONE
    @PostMapping("api/user")
    public User createUser(@Valid @RequestBody User user) throws ResourceNotFoundException {
        return userService.addUser(user);
    }

    //DONE
    @PostMapping("api/group")
    public Group createGroup(@Valid @RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @PostMapping("api/car")
    public Car createCar(@Valid @RequestBody Car car) throws ResourceNotFoundException {

        // verificar se não existe um carro com a mesma matricula
        if (carService.existsByMatricula(car.getMatricula())) {
            System.out.println("Car already exists");
            throw new ResourceNotFoundException("Car already exists");
        }
        try {
            return carService.addCar(car);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving car");
        }
    }

    // //adiconar user a group
    // @PostMapping("api/user/{user_id}/group")
    // public Group addUser(@Valid @RequestBody String name) throws ResourceNotFoundException{

    // }
    
//    @PostMapping("api/user/{user_id}/group/{group_id}/car")
//    public List<Car> addCarToGroup(@PathVariable Long group_id, @PathVariable Long user_id, @Valid @RequestBody Car car) throws ResourceNotFoundException {
//        //verificar que o user é o dono do grupo
//        if(!groupService.verifyAdmin(user_id, group_id)){
//            throw new ResourceNotFoundException("User is not admin of the group");
//        }
//        //criar o carro
//        Car new_car = carService.addCar(car);
//        //adicionar o carro ao grupo
//        return groupService.addCarToGroup(group_id, new_car.getId());
//    }
}

