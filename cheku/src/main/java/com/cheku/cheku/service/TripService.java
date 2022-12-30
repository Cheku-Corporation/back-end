package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheku.cheku.auxiliar_classes.*;
import com.cheku.cheku.exception.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private VelocityService velocityService;

    @Autowired
    private FluidConsumption fluidService;

    @Autowired
    private CarService carService;


    public void deleteTrips() throws ResourceNotFoundException {
        tripRepository.deleteAll();;
    }

    public Trip addTrip(Trip trip) throws ResourceNotFoundException {
        return tripRepository.save(trip);
    }

    public void changeDistance(Long trip_id, Double newDistance) {
        tripRepository.changeTripDistanceTraveled(trip_id, newDistance);
    }

    public void finishTrip(Long trip_id, long timestamp, Double latitude, Double longitude) {
        tripRepository.finishTrip(trip_id, new Date(timestamp), latitude, longitude);
    }

    public Long getCarCurrentTripNumber(Long car_id) {
        return tripRepository.getNumbersByCar(car_id);
    }

    public Trip getCarCurrentTrip(Long car_id) {
        return tripRepository.getCurrentCarTrip(car_id);
    }

    public Trip getTripWithCarAndNumber(Long car_id, Long number) throws ResourceNotFoundException {
        return tripRepository.getTripWithCarAndNumber(car_id, number);
    }

    public boolean isOnTheRoad(Long car_id) {
        return tripRepository.getCurrentCarTrip(car_id).getEndTime() == null;
    }

    public List<Trip> getAll() {
        return tripRepository.findAll(); 
    }

    public long getThisTripTime(Long car_id) {
        return (velocityService.getLastVelocity(car_id).getDate().getTime() -
        tripRepository.getCurrentCarTrip(car_id).getStartTime().getTime()) / 1000;
    }

    public Double getTotalDistance(Long car_id) {
        List<Trip> trips = tripRepository.findAllPassed(car_id);
        System.out.println("Number of trips: " + trips.size() );
        if(trips.size() == 0) {
            return 0.0;
        }
        Double distance = 0.0d;
        for (Trip t : trips) {
            distance += t.getTraveledDistance();
        }
        return distance; 
    }

    public TripOverview getLastOverview(Long car_id) {
        List<Trip> trips = tripRepository.findAllPassed(car_id);
        System.out.println("Number of trips: " + trips.size() );
        if(trips.size() < 1) {
            throw new RuntimeException("Not enougth data to show");
        }
        int numberOfTrips = 0;
        Double distance = 0.0d;
        Double time = 0.0d;
        Double fuel = 0.0d;
        TripOverview trip = new TripOverview();
        for (Trip t : trips) {
            distance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            time += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            Double fuel_init = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getEndTime()); 
            Double fuel_final = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getStartTime()); 
            fuel += fuel_final - fuel_init;

            MapPositions thisTripCoordenates = new MapPositions();
            thisTripCoordenates.setTripNumber(numberOfTrips + 1);
            thisTripCoordenates.setStart(new Coordinates(t.getStartLatitude(), t.getStartLongitude()));
            thisTripCoordenates.setEnd(new Coordinates(t.getEndLatitude(), t.getEndLongitude()));
            trip.addCoordinate(thisTripCoordenates);
            if(++numberOfTrips == 1) {
                break;
            }
        }
        try {
            //Double fuelSpentInLitters = fuel * carService.getCar(car_id).getCarModel().getTankCapacity() / 100;
            trip.setFuelSpent(fuel * carService.getCar(car_id).getCarModel().getTankCapacity() / 100);
        } catch (ResourceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        trip.setTotalDistance(distance);
        trip.setTotalTime(time);
        Double averageSpeed = distance / time;
        trip.setAverageSpeed(averageSpeed);
        Double consumption = (trip.getFuelSpent() * 100) / trip.getTotalDistance();
        trip.setFuelConsumptionPer100km(consumption);

        if(trips.size() - numberOfTrips < 1) {
            trip.setPreviousHasData(false);
            return trip; 
        }
        trip.setPreviousHasData(true);
        Double previousDistance = 0.0d;
        Double previousTime = 0.0d;
        Double previousFuel = 0.0d;
        for (Trip t : trips.subList(numberOfTrips, trips.size())) {
            previousDistance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            previousTime += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            Double fuel_init = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getEndTime()); 
            Double fuel_final = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getStartTime()); 
            previousFuel += fuel_final - fuel_init;
            if(++numberOfTrips == 2) {
                break;
            }
        }
        System.out.println(previousDistance + " " + previousTime);
        Double previousAverageSpeed = previousDistance / previousTime;
        trip.setFuelSpentComparedToPrevious((fuel - previousFuel) * 100 / previousTime);
        trip.setAverageSpeedComparedToPrevious((averageSpeed - previousAverageSpeed) * 100 / previousAverageSpeed);
        trip.setTotalTimeComparedToPrevious((time - previousTime) * 100 / previousTime);
        trip.setTotalDistanceComparedToPrevious((distance - previousDistance) * 100 / previousDistance);
        Double previousConsumption = (previousFuel * 100) / previousDistance;
        trip.setFuelConsumptionPer100kmComparedToPrevious((consumption - previousConsumption) * 100 / previousConsumption);
        return trip;
    }

    public TripOverview getWeekOverview(Long car_id) {
        List<Trip> trips = tripRepository.findAllPassed(car_id);
        System.out.println("Number of trips: " + trips.size() );
        if(trips.size() < 7) {
            throw new RuntimeException("Not enougth data to show");
        }
        int numberOfTrips = 0;
        Double distance = 0.0d;
        Double time = 0.0d;
        Double fuel = 0.0d;
        TripOverview trip = new TripOverview();
        for (Trip t : trips) {
            distance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            time += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            Double fuel_init = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getEndTime()); 
            Double fuel_final = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getStartTime()); 
            fuel += fuel_final - fuel_init;

            MapPositions thisTripCoordenates = new MapPositions();
            thisTripCoordenates.setTripNumber(numberOfTrips + 1);
            thisTripCoordenates.setStart(new Coordinates(t.getStartLatitude(), t.getStartLongitude()));
            thisTripCoordenates.setEnd(new Coordinates(t.getEndLatitude(), t.getEndLongitude()));
            trip.addCoordinate(thisTripCoordenates);
            if(++numberOfTrips == 7) {
                break;
            }
        }
        try {
            //Double fuelSpentInLitters = fuel * carService.getCar(car_id).getCarModel().getTankCapacity() / 100;
            trip.setFuelSpent(fuel * carService.getCar(car_id).getCarModel().getTankCapacity() / 100);
        } catch (ResourceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        trip.setTotalDistance(distance);
        trip.setTotalTime(time);
        Double averageSpeed = distance / time;
        trip.setAverageSpeed(averageSpeed);
        Double consumption = (trip.getFuelSpent() * 100) / trip.getTotalDistance();
        trip.setFuelConsumptionPer100km(consumption);

        if(trips.size() - numberOfTrips < 7) {
            trip.setPreviousHasData(false);
            return trip; 
        }
        trip.setPreviousHasData(true);
        Double previousDistance = 0.0d;
        Double previousTime = 0.0d;
        Double previousFuel = 0.0d;
        for (Trip t : trips.subList(numberOfTrips, trips.size())) {
            previousDistance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            previousTime += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            Double fuel_init = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getEndTime()); 
            Double fuel_final = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getStartTime()); 
            previousFuel += fuel_final - fuel_init;
            if(++numberOfTrips == 14) {
                break;
            }
        }
        System.out.println(previousDistance + " " + previousTime);
        Double previousAverageSpeed = previousDistance / previousTime;
        trip.setFuelSpentComparedToPrevious((fuel - previousFuel) * 100 / previousTime);
        trip.setAverageSpeedComparedToPrevious((averageSpeed - previousAverageSpeed) * 100 / previousAverageSpeed);
        trip.setTotalTimeComparedToPrevious((time - previousTime) * 100 / previousTime);
        trip.setTotalDistanceComparedToPrevious((distance - previousDistance) * 100 / previousDistance);
        Double previousConsumption = (previousFuel * 100) / previousDistance;
        trip.setFuelConsumptionPer100kmComparedToPrevious((consumption - previousConsumption) * 100 / previousConsumption);
        return trip;
    }

    public TripOverview getMonthOverview(Long car_id) {
        List<Trip> trips = tripRepository.findAllPassed(car_id);
        System.out.println("Number of trips: " + trips.size() );
        if(trips.size() < 24) {
            throw new RuntimeException("Not enougth data to show");
        }
        int numberOfTrips = 0;
        Double distance = 0.0d;
        Double time = 0.0d;
        Double fuel = 0.0d;
        TripOverview trip = new TripOverview();
        for (Trip t : trips) {
            distance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            time += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            Double fuel_init = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getEndTime()); 
            Double fuel_final = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getStartTime()); 
            fuel += fuel_final - fuel_init;

            MapPositions thisTripCoordenates = new MapPositions();
            thisTripCoordenates.setTripNumber(numberOfTrips + 1);
            thisTripCoordenates.setStart(new Coordinates(t.getStartLatitude(), t.getStartLongitude()));
            thisTripCoordenates.setEnd(new Coordinates(t.getEndLatitude(), t.getEndLongitude()));
            trip.addCoordinate(thisTripCoordenates);
            if(++numberOfTrips == 24) {
                break;
            }
        }
        try {
            //Double fuelSpentInLitters = fuel * carService.getCar(car_id).getCarModel().getTankCapacity() / 100;
            trip.setFuelSpent(fuel * carService.getCar(car_id).getCarModel().getTankCapacity() / 100);
        } catch (ResourceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        trip.setTotalDistance(distance);
        trip.setTotalTime(time);
        Double averageSpeed = distance / time;
        trip.setAverageSpeed(averageSpeed);
        Double consumption = (trip.getFuelSpent() * 100) / trip.getTotalDistance();
        trip.setFuelConsumptionPer100km(consumption);

        if(trips.size() - numberOfTrips < 7) {
            trip.setPreviousHasData(false);
            return trip; 
        }
        trip.setPreviousHasData(true);
        Double previousDistance = 0.0d;
        Double previousTime = 0.0d;
        Double previousFuel = 0.0d;
        for (Trip t : trips.subList(numberOfTrips, trips.size())) {
            previousDistance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            previousTime += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            Double fuel_init = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getEndTime()); 
            Double fuel_final = fluidService.getFuelInLitersByTimestamp(t.getId(), t.getStartTime()); 
            previousFuel += fuel_final - fuel_init;
            if(++numberOfTrips == 14) {
                break;
            }
        }
        System.out.println(previousDistance + " " + previousTime);
        Double previousAverageSpeed = previousDistance / previousTime;
        trip.setFuelSpentComparedToPrevious((fuel - previousFuel) * 100 / previousTime);
        trip.setAverageSpeedComparedToPrevious((averageSpeed - previousAverageSpeed) * 100 / previousAverageSpeed);
        trip.setTotalTimeComparedToPrevious((time - previousTime) * 100 / previousTime);
        trip.setTotalDistanceComparedToPrevious((distance - previousDistance) * 100 / previousDistance);
        Double previousConsumption = (previousFuel * 100) / previousDistance;
        trip.setFuelConsumptionPer100kmComparedToPrevious((consumption - previousConsumption) * 100 / previousConsumption);
        return trip;
    }
}