package com.cheku.cheku.api;

import java.util.List;

import com.cheku.cheku.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.auxiliar_classes.LiveStatus;
import com.cheku.cheku.auxiliar_classes.ProcessedUser;
import com.cheku.cheku.auxiliar_classes.SimpleFluid;
import com.cheku.cheku.auxiliar_classes.UserNotification;
import com.cheku.cheku.auxiliar_classes.Velocity;
import com.cheku.cheku.auxiliar_classes.LiveStatus.LiveStatusBuilder;
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
	private PneusHistoryService pneusHistoryService;

	@Autowired
	private LightsService luzesService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private FluidService fluidService;

	@Autowired
	private TripService tripService;

	@Autowired
	private PneusHistoryService tireService;

	@Autowired
	private NotificationService notificationService;

	@GetMapping("api/cars")
	public List<Car> getCars() {
		return carservice.getAllCars();
	}

	@GetMapping("api/trips")
	public List<Trip> getTrips() {
		return tripService.getAll();
	}

	@GetMapping("api/localizations")
	public List<Localization> getLocs() {
		return localizationService.getAll();
	}

	//Done
	@GetMapping("api/car/{car_id}")
	public Car getCar(@PathVariable Long car_id) throws ResourceNotFoundException {
		return carservice.getCar(car_id);
	}

	//@GetMapping("api/group/{group_id}/cars")



	@GetMapping("api/users")
	public List<ProcessedUser> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("api/groups")
	public List<Group> getGroups(){
		return groupService.getAllGroups();
	}

	@GetMapping("api/users/{user_id}/groups")
	public List<Group> getUserGroups(@PathVariable Long user_id){
		return groupService.getUserGroups(user_id);
	}

	@GetMapping("api/car/{car_id}/velocities/100")
	public List<Velocity> get100CarVelocities(@PathVariable Long car_id) {
		return velocityService.getLast100Velocities(car_id);
	}

	@GetMapping("api/car/{car_id}/velocities/1000")
	public List<Velocity> get1000CarVelocities(@PathVariable Long car_id) {
		return velocityService.getLast1000Velocities(car_id);
	}

	@GetMapping("api/car/{car_id}/notifications")
	public List<UserNotification> getCarNotifications(@PathVariable Long car_id) {
		return notificationService.getAllNotifications(car_id);
	}

	@GetMapping("api/lasttrip")
	public List<Trip> getLastTripInfo(@RequestParam(value = "carid") Long car_id) {
		return null;
	}

	@GetMapping("api/lastweek")
	public List<Trip> getLastWeekTripInfo(@RequestParam(value = "carid") Long car_id) {
		return null;
	}

	@GetMapping("api/lastmonth")
	public List<Trip> getLastMonthTripInfo(@RequestParam(value = "carid") Long car_id) {
		return null;
	}

	@GetMapping("api/live")
	public LiveStatus getLive(@RequestParam(value = "carid") Long car_id) {
		LiveStatus ls = new LiveStatusBuilder()
			.setSpeed(velocityService.getLastVelocity(car_id))
			.setGear(velocityService.getLastVelocity(car_id))
			.setRPM(velocityService.getLastVelocity(car_id))
			// .setTiresPressure(tireService.getLastTireState(car_id))
			// .setTiresTemperature(tireService.getLastTireState(car_id))
			.setTotalDistance(tripService.getCarCurrentTrip(car_id))
			.build();
		return ls;
	}
}
