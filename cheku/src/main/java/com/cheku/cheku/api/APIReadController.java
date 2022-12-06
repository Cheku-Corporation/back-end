package com.cheku.cheku.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	private MotorHistoryService motorHistoryService;

	@Autowired
	private PneusHistoryService pneusHistoryService;

	@Autowired
	private LuzesService luzesService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;


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

	@GetMapping("api/users")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("api/groups")
	public List<Group> getGroups(){
		return groupService.getAllGroups();
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
	public List<Velocity_history> getCarVelocities() {
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

	@GetMapping("api/motorHistory")
	public List<MotorHistory> getMotorHistory() {
		return motorHistoryService.getAllMotorHistory();
	}

	@GetMapping("api/pneusHistory")
	public List<PneusHistory> getPneusHistory() {
		return pneusHistoryService.getAllPneusHistory();
	}

	@GetMapping("api/Luzes")
	public List<Luzes> getLuzes() {
		return luzesService.getAllLuzes();
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
