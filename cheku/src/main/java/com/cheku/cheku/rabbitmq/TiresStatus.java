package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.LightsHistory;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.LightsService;
import com.cheku.cheku.service.PneusHistoryService;
import com.cheku.cheku.service.TripService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TiresStatus {
    
    @Autowired
    private PneusHistoryService pneusService;

    @Autowired
    private TripService tripService;

    @Autowired
    private CarService carService;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) throws ResourceNotFoundException {
        String msg = new String(message);
        System.out.println("Received Tire <" + msg + ">");
        // velocityStorage.addVelocity(msg);
        JSONObject j = new JSONObject(msg);
        if(carService.existsById((long) 1)) {
            Car car = carService.getCar((long) 1);
            LightsHistory lights = new LightsHistory(); 
            lights.setCar(car);
            //lights.setLatitude(j.getString("current_fuel"));
            //lights.setLongitude(j.getString("current_oil")); 
            //lights.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
      
            try {
                //lightsService.getAllLuzes();
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
