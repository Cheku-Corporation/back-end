package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.LightsHistory;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.LightsService;
import com.cheku.cheku.service.PneusHistoryService;
import com.cheku.cheku.service.TripService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MotorStatus {
    
    @Autowired
    private TripService tripService;

    @Autowired
    private CarService carService;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) throws ResourceNotFoundException {
        String msg = new String(message);
        System.out.println("Received Motor <" + msg + ">");
        JSONObject j = new JSONObject(msg);
        if(carService.existsById((long) 1)) {
            Car car = carService.getCar((long) 1);
            if(j.getString("motor_status").compareTo("ON") == 0) {
                Trip trip = new Trip();
                trip.setCar(car);
                trip.setStartTime(new Date((long) (j.getDouble("timestamp") * 1000)));
                trip.setTraveledDistance(0.0);
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
                trip.setEndTime(new Date((long) (j.getDouble("timestamp") * 1000)));
                System.out.println("Finish");
            }
        } 
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
