package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
import com.cheku.cheku.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private MotorService motorService;

    @Autowired
    private PneusService pneusService;


    //DONE
    @PostMapping("admin/motor")
    public Motor createMotor(@Valid @RequestBody Motor motor) throws ResourceNotFoundException {
        return motorService.addMotor(motor);
    }

    //DONE
    @PostMapping("admin/pneus")
    public Pneus createPneus(@Valid @RequestBody Pneus pneus) throws ResourceNotFoundException {
        return pneusService.addPneus(pneus);
    }

    @PostMapping("admin/carModel")
    public CarModel createCar(@Valid @RequestBody CarModel car)  throws  ResourceNotFoundException {
        return carModelService.createCarModel(car);
    }

}
