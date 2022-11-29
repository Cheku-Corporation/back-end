package com.cheku.cheku.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.HistoryVelocity;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.VelocityService;
import com.cheku.cheku.exception.ResourceNotFoundException;

@RestController
public class APICreateController {
    
    @Autowired
	private CarService carService;

    @Autowired
	private VelocityService velocityService;

//    @PostMapping("api/velocity")
//    public void createVelocityRecord(@Valid @RequestBody HistoryVelocity velocity) throws ResourceNotFoundException{
//        VelocityService.saveitem(velocity);
//
//    }
    //DONE
	@PostMapping("api/car")
    public Car createCar(@Valid @RequestBody Car car) {
        return carService.addCar(car);
	}
}
