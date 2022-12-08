package com.cheku.cheku.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.auxiliar_classes.ProcessedUser;
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
	private MotorHistoryService motorHistoryService;

	@Autowired
	private PneusHistoryService pneusHistoryService;

	@Autowired
	private LuzesService luzesService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private FluidService fluidService;

	@GetMapping("api/cars")
	public List<Car> getCars() {
		return carservice.getAllCars();
	}

	//Done
	@GetMapping("api/car/{car_id}")
	public Car getCar(@PathVariable Long car_id) {
		return carservice.getCar(car_id);
	}

	//@GetMapping("api/group/{group_id}/cars")


	//Done
	@GetMapping("api/motors")
	public List<Motor> getMotors() {
		return motorservice.getAllMotors();
	}


	//Done
	@GetMapping("api/pneus")
	public List<Pneus> getPneus() {
		return pneusService.getAllPneus();
	}

	//Done
	@GetMapping("api/users")
	public List<ProcessedUser> getUsers() {
		return userService.getAllUsers();
	}

	//Done
	@GetMapping("api/groups")
	public List<Group> getGroups(){
		return groupService.getAllGroups();
	}

	//Done
	@GetMapping("api/users/{user_id}/groups")
	public List<Group> getUserGroups(@PathVariable Long user_id){
		return groupService.getUserGroups(user_id);
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


	// --------------------------------------------------------------------------------------------------------------
	//Done (Should not be used!)
	@GetMapping("api/localizations")
	public List<Localization> getLocalizations() {
		return localizationService.getAllLocalizations();
	}


	//Done (Should not be used!)
	@GetMapping("api/velocities")
	public List<Velocity_history> getCarVelocities() {
		return velocityService.getAllVelocity();
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

	@GetMapping("api/fluids")
	public List<Fluid> getFluids() {
		return fluidService.getAllFluids();
	}










}
