package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.CarModel;
import com.cheku.cheku.model.Group;
import com.cheku.cheku.model.Motor;
import com.cheku.cheku.model.Tires;
import com.cheku.cheku.model.dto.CarModelDTO;
import com.cheku.cheku.model.dto.UserDTO;
import com.cheku.cheku.service.CarModelService;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.GroupService;
import com.cheku.cheku.service.MotorService;
import com.cheku.cheku.service.TiresService;
import com.cheku.cheku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for handling requests to the /admin endpoints.
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CarModelService carModelService;
    private final MotorService motorService;
    private final TiresService tiresService;
    private final CarService carService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public AdminController(CarModelService carModelService, MotorService motorService, TiresService tiresService,
                           CarService carService, GroupService groupService, UserService userService) {
        this.carModelService = carModelService;
        this.motorService = motorService;
        this.tiresService = tiresService;
        this.carService = carService;
        this.groupService = groupService;
        this.userService = userService;
    }

    /** Creates a new motor.*/
    @PostMapping("motor")
    public Motor createMotor(@Valid @RequestBody Motor motor) throws ResourceNotFoundException {
        return motorService.addMotor(motor);
    }

    /** Creates a new tires.*/
    @PostMapping("tires")
    public Tires createPneus(@Valid @RequestBody Tires tires) throws ResourceNotFoundException {
        return tiresService.addTires(tires);
    }

    /** Creates a new car model.*/
    @PostMapping("carModel")
    public CarModel createCar(@Valid @RequestBody CarModel car) throws ResourceNotFoundException {
        return carModelService.createCarModel(car);
    }

    /** Returns a list of all motors.*/
    @GetMapping("motors")
    public List<Motor> getMotors() {
        return motorService.getAllMotors();
    }

    /** Returns a list of all tires.*/
    @GetMapping("tires")
    public List<Tires> getTires() {
        return tiresService.getAllTires();
    }

    /** Returns a list of all car models.*/
    @GetMapping("carModels")
    public List<CarModelDTO> getCarModels() {
        return carModelService.getAllCarModels();
    }

    /** Returns a list of all cars.*/
    @GetMapping("cars")
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    /** Returns a list of all users.*/
    @GetMapping("users")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    /** Returns a list of all groups.*/
    @GetMapping("groups")
    public List<Group> getGroups() {
        return groupService.getAllGroups();
    }
}

