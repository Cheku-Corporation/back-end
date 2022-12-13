package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;
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
    private PneusService pneusService;


    //DONE
    @PostMapping("motor")
    public Motor createMotor(@Valid @RequestBody Motor motor) throws ResourceNotFoundException {
        return motorService.addMotor(motor);
    }

    //DONE
    @PostMapping("pneus")
    public Pneus createPneus(@Valid @RequestBody Pneus pneus) throws ResourceNotFoundException {
        return pneusService.addPneus(pneus);
    }

    @PostMapping("carModel")
    public CarModel createCar(@Valid @RequestBody CarModel car)  throws  ResourceNotFoundException {
        return carModelService.createCarModel(car);
    }

    //Done
	@GetMapping("motors")
	public List<Motor> getMotors() {
		return motorService.getAllMotors();
	}

	//Done
	@GetMapping("pneus")
	public List<Pneus> getPneus() {
		return pneusService.getAllPneus();
	}

}
