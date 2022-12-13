package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.PneusHistory;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.PneusHistoryService;
import com.cheku.cheku.service.TripService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TiresStatus {
    
    @Autowired
    private PneusHistoryService tiresService;

    @Autowired
    private TripService tripService;

    @Autowired
    private CarService carService;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) throws ResourceNotFoundException {
        String msg = new String(message);
        System.out.println("Received Tire <" + msg + ">");

        JSONObject j = new JSONObject(msg);
        if(carService.existsById((long) 1)) {
            Car car = carService.getCar((long) 1);
            Trip trip = tripService.getCarCurrentTrip(car.getId());
            PneusHistory tire = new PneusHistory(); 
            tire.setTrip(trip);
            tire.setPressure(j.getDouble("tires_pressure"));
            tire.setTemperature(j.getDouble("tires_temperature"));
            tire.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
            tiresService.savePneusHistory(tire);
        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
