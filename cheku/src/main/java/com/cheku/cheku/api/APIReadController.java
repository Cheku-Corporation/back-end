package com.cheku.cheku.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.*;
import com.cheku.cheku.service.*;

@RestController
public class APIReadController {
    
	@Autowired
	private VelocityService velocityService;

	@Autowired
	private CarService carservice;

	@Autowired
	private MotorService motorservice;

	//Done
	@GetMapping("api/velocitys")
	public List<HistoryVelocity> getCarVelocities() {
		return velocityService.getAllVelocity();
	}

	//Done
	@GetMapping("api/cars")
	public List<Car> getCars() {
		return carservice.getAllCars();
	}

	//Done
	@GetMapping("api/motors")
	public List<Motor> getMotors() {
		return motorservice.getAllMotors();
	}

}
