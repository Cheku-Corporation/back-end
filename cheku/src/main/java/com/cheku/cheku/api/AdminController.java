package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.model.dto.CarModelDTO;
import com.cheku.cheku.model.dto.UserDTO;
import com.cheku.cheku.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private MotorService motorService;

    @Autowired
    private TiresService tiresService;
    @Autowired
    private CarService carService;
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;


    @PostMapping("motor")
    public Motor createMotor(@Valid @RequestBody Motor motor) throws ResourceNotFoundException {
        return motorService.addMotor(motor);
    }

    @PostMapping("tires")
    public Tires createPneus(@Valid @RequestBody Tires tires) throws ResourceNotFoundException {
        return tiresService.addTires(tires);
    }

    @PostMapping("carModel")
    public CarModel createCar(@Valid @RequestBody CarModel car)  throws  ResourceNotFoundException {
        return carModelService.createCarModel(car);
    }

	@GetMapping("motors")
	public List<Motor> getMotors() {
		return motorService.getAllMotors();
	}

	@GetMapping("tires")
	public List<Tires> getTires() {
        return tiresService.getAllTires();
    }
    @GetMapping("carModels")
    public List<CarModelDTO> getCarModels() {
        return carModelService.getAllCarModels();
    }
    @GetMapping("cars")
    public List<Car> getCars() {
        return carService.getAllCars();
    }
    @GetMapping("users")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("groups")
    public List<Group> getGroups(){
        return groupService.getAllGroups();
    }


}
