package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.TripService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarStatus {
    
    @Autowired
    private TripService tripService;

    @Autowired
    private CarService carService;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) throws ResourceNotFoundException {
        String msg = new String(message);
        System.out.println("Received Car Status <" + msg + ">");
        JSONObject j = new JSONObject(msg);
        if(carService.existsById(j.getLong("id"))) {
            Car car = carService.getCar(j.getLong("id"));
            if(j.getString("motor_status").compareTo("ON") == 0) {
                Trip trip = new Trip();
                trip.setCar(car);
                trip.setStartTime(new Date((long) (j.getDouble("timestamp") * 1000)));
                trip.setTraveledDistance(0.0);
                trip.setStartLatitude(((Double)j.getDouble("latitude")));
                trip.setStartLongitude(((Double)j.getDouble("longitude")));
                System.out.println( tripService.getCarCurrentTripNumber(car.getId()) );
                if(tripService.getCarCurrentTripNumber(car.getId()) == null) {
                    trip.setNumber(Long.parseLong("1"));
                } else {
                    trip.setNumber(tripService.getCarCurrentTripNumber(car.getId()) + (long) 1);
                }
                try {
                    tripService.addTrip(trip);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //System.out.println("New trip" + trip);

            } else if(j.getString("motor_status").compareTo("OFF") == 0) {
            
                System.out.println( tripService.getCarCurrentTripNumber(car.getId()) );
                Long tripNumber = tripService.getCarCurrentTripNumber(car.getId());
                Trip trip = tripService.getTripWithCarAndNumber(car.getId(), tripNumber);
                tripService.finishTrip(trip.getId(), (long) (j.getDouble("timestamp") * 1000), 
                            ((Double)j.getDouble("latitude")), ((Double)j.getDouble("longitude")));
                System.out.println("Finish");
            }
        } 
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
