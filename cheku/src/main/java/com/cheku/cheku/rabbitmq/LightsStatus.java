package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.LightsHistory;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.model.enums.LightState;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.LightsService;
import com.cheku.cheku.service.TripService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LightsStatus {
    
    @Autowired
    private LightsService lightsService;

    @Autowired
    private TripService tripService;

    @Autowired
    private CarService carService;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) throws ResourceNotFoundException {
        String msg = new String(message);
        System.out.println("Received Light <" + msg + ">");

        JSONObject j = new JSONObject(msg);
        if(carService.existsById((long) 1)) {
            Car car = carService.getCar((long) 1);
            Trip trip = tripService.getCarCurrentTrip(car.getId());
            LightsHistory lights = new LightsHistory(); 
            lights.setTrip(trip);
            lights.setState(LightState.valueOf( j.getString("lights")));
            lights.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
            lightsService.save(lights);
        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
