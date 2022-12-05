package com.cheku.cheku.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.auxiliar_classes.Velocity;
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

	@Autowired
	private PneusService pneusService;

	@Autowired
	private LocalizationService localizationService;

	@Autowired
	private CombustivelService combustivelService;

	@Autowired
	private OleoService oleoService;

	@Autowired
	private AguaService aguaService;



	@GetMapping("api/cars")
	public List<Car> getCars() {
		return carservice.getAllCars();
	}


	@GetMapping("api/car/{car_id}")
	public Car getCar(@PathVariable Long car_id) {
		return carservice.getCar(car_id);
	}


	@GetMapping("api/motors")
	public List<Motor> getMotors() {
		return motorservice.getAllMotors();
	}


	@GetMapping("api/pneus")
	public List<Pneus> getPneus() {
		return pneusService.getAllPneus();
	}

	//Done (Should not be used!)
	@GetMapping("api/localizations")
	public List<Localization> getLocalizations() {
		return localizationService.getAllLocalizations();
	}

	//Done (Should not be used!)
	@GetMapping("api/combustiveis")
	public List<Combustivel> getCombustivel() {
		return combustivelService.getAllCombustiveis();
	}
	//Done (Should not be used!)
	@GetMapping("api/velocities")
	public List<SpeedHistory> getCarVelocities() {
		return velocityService.getAllVelocity();
	}

	//Done (Should not be used!)
	@GetMapping("api/oleos")
	public List<Oleo> getOleo() {
		return oleoService.getAllOleos();
	}

	//Done (Should not be used!)
	@GetMapping("api/aguas")
	public List<Agua> getAgua() {
		return aguaService.getAllAguas();
	}
	//Done
	@GetMapping("api/car/{car_id}/velocities/100")
	public List<Velocity> get100CarVelocities(@PathVariable Long car_id) {
		return velocityService.getLast100Velocities(car_id);
	}

	//Done
	@GetMapping("api/car/{car_id}/velocities/1000")
	public List<Velocity> get1000CarVelocities(@PathVariable Long car_id) {
		return velocityService.getLast1000Velocities(car_id);
	}



}
