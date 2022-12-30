package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.Localization;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.LocalizationService;
import com.cheku.cheku.service.TripService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripCoordinates {
    
    @Autowired
    private CarService carService;

    @Autowired
    private TripService tripService;

    @Autowired
    private LocalizationService locationservice;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) throws ResourceNotFoundException {
        String msg = new String(message);
        System.out.println("Received coord <" + msg + ">");
        // velocityStorage.addVelocity(msg);
        JSONObject j = new JSONObject(msg);
        if(carService.existsById((long) 1)) {
            Car car = carService.getCar((long) 1);
            Trip trip = tripService.getCarCurrentTrip(car.getId());
            Localization localization = new Localization(); 
            localization.setTrip(trip);
            localization.setLatitude(j.getDouble("latitude"));
            localization.setLongitude(j.getDouble("longitude")); 
            localization.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
            locationservice.addLocalization(localization);
        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
