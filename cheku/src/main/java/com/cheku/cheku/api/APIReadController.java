package com.cheku.cheku.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.HistoryVelocity;
import com.cheku.cheku.service.*;

@RestController
public class APIReadController {
    
	@Autowired
	private VelocityService velocityService;

	@Autowired
	private CarService carservice;

	@GetMapping("api/velocity")
	public List<HistoryVelocity> getCarVelocities() {
		//return movieService.getMovies();
		velocityService.saveitem(new HistoryVelocity((Double) 10.0));
		List<HistoryVelocity> vel = velocityService.getAllVelocity();
		return vel;
	}

	@GetMapping("api/car")
	public List<Car> getCars() {
		//return movieService.getMovies();
		List<Car> cars = carservice.getAllCars();
		System.out.print(cars);
		return cars;
	}

}
