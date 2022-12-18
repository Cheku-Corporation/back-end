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
            throw new RuntimeException("Not enougth data to show");
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
        TripOverview trip = new TripOverview();
        for (Trip t : trips) {
            distance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            time += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            
            MapPositions thisTripCoordenates = new MapPositions();
            thisTripCoordenates.setTripNumber(numberOfTrips + 1);
            thisTripCoordenates.setStart(new Coordinates(t.getStartLatitude(), t.getStartLongitude()));
            thisTripCoordenates.setEnd(new Coordinates(t.getEndLatitude(), t.getEndLongitude()));
            trip.addCoordinate(thisTripCoordenates);
            if(++numberOfTrips == 1) {
                break;
            }
        }
        trip.setTotalDistance(distance);
        trip.setTotalTime(time);
        trip.setAverageSpeed(distance / time);
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
        TripOverview trip = new TripOverview();
        for (Trip t : trips) {
            distance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            time += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            
            MapPositions thisTripCoordenates = new MapPositions();
            thisTripCoordenates.setTripNumber(numberOfTrips + 1);
            thisTripCoordenates.setStart(new Coordinates(t.getStartLatitude(), t.getStartLongitude()));
            thisTripCoordenates.setEnd(new Coordinates(t.getEndLatitude(), t.getEndLongitude()));
            trip.addCoordinate(thisTripCoordenates);
            if(++numberOfTrips == 7) {
                break;
            }
        }
        trip.setTotalDistance(distance);
        trip.setTotalTime(time);
        trip.setAverageSpeed(distance / time);
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
        TripOverview trip = new TripOverview();
        for (Trip t : trips) {
            distance += t.getTraveledDistance();
            long diffInMillies = t.getEndTime().getTime() - t.getStartTime().getTime();
            time += TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.SECONDS);
            
            MapPositions thisTripCoordenates = new MapPositions();
            thisTripCoordenates.setTripNumber(numberOfTrips + 1);
            thisTripCoordenates.setStart(new Coordinates(t.getStartLatitude(), t.getStartLongitude()));
            thisTripCoordenates.setEnd(new Coordinates(t.getEndLatitude(), t.getEndLongitude()));
            trip.addCoordinate(thisTripCoordenates);
            if(++numberOfTrips == 24) {
                break;
            }
        }
        trip.setTotalDistance(distance);
        trip.setTotalTime(time);
        trip.setAverageSpeed(distance / time);
        return trip;
    }
}