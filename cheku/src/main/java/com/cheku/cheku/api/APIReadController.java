package com.cheku.cheku.api;

import java.util.List;
import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.dto.CarModelDTO;
import com.cheku.cheku.model.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cheku.cheku.auxiliar_classes.*;
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
	private PneusHistoryService pneusHistoryService;

	@Autowired
	private LightsService lightsService;

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

	@Autowired
	private CarModelService carModelService;

	@Autowired
	private PneusHistoryService tHistoryService;

	@GetMapping("api/trips")
	public List<Trip> getTrips() {
		return tripService.getAll();
	}

	@GetMapping("api/tires")
	public List<PneusHistory> getHistoryPneus() {
		return tHistoryService.getAllPneusHistory();
	}

	@GetMapping("api/lights")
	public List<LightsHistory> getHistoryLights() {
		return lightsService.getAllLuzes();
	}

	@GetMapping("api/car/{car_id}")
	public Car getCar(@PathVariable Long car_id) throws ResourceNotFoundException {
		return carservice.getCar(car_id);
	}

	@GetMapping("api/group/cars")
	public List<Car> getCars(@RequestBody String data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Long groupId = mapper.readTree(data).get("groupId").asLong();

		//verificar se o grupo existe
		System.out.println("Group id: " + groupService.findGroupById(groupId));
		if (!groupService.findGroupById(groupId)) {
			throw new RuntimeException("The group does not exist");
		}

		try {
			return groupService.ListCarInGroup(groupId);
		} catch (Exception e) {
			throw new RuntimeException("Error getting cars");
		}
	}
	@GetMapping("api/carModels")
	public List<CarModelDTO> getCarModels() {
		return carModelService.getAllCarModels();
	}

	@GetMapping("api/user/{user_email}")
	public UserDTO getCurrentUser(@PathVariable String user_email) throws JsonProcessingException {
		return userService.getCurrentUser(user_email);
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
	public TripOverview getLastTripInfo(@RequestParam(value = "carid") Long car_id) {
		return tripService.getLastOverview(car_id);
	}

	@GetMapping("api/lastweek")
	public TripOverview getLastWeekTripInfo(@RequestParam(value = "carid") Long car_id) {
		return tripService.getWeekOverview(car_id);
	}

	@GetMapping("api/lastmonth")
	public TripOverview getLastMonthTripInfo(@RequestParam(value = "carid") Long car_id) {
		return tripService.getMonthOverview(car_id);
	}

	@GetMapping("api/live")
	public LiveStatus getLive(@RequestParam(value = "carid") Long car_id) {
		return new LiveStatusBuilder()
			.setSpeed(velocityService.getLastVelocity(car_id))
			.setGear(velocityService.getLastVelocity(car_id))
			.setRPM(velocityService.getLastVelocity(car_id))
			.setTiresPressure(tireService.getLastTireState(car_id))
			.setTiresTemperature(tireService.getLastTireState(car_id))
			.setLightsState(lightsService.getLastLightState(car_id))
			.setRelativeDistance(tripService.getCarCurrentTrip(car_id))
			.setTotalDistance(tripService.getTotalDistance(car_id))
			.setTime(tripService.getThisTripTime(car_id))
			.setOil(fluidService.getLastOilPercentage(car_id))
			.setWater(fluidService.getLastWaterPercentage(car_id))
			.setFuel(fluidService.getLastFuelInLiters(car_id))
			.build();
	}
}
