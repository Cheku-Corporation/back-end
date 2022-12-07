package com.cheku.cheku.rabbitmq;

import java.util.concurrent.CountDownLatch;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.VelocityService;

@Component
public class Fluids {
    
    @Autowired
    private VelocityService velocityService;

    @Autowired
    private CarService carService;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) {
        String msg = new String(message);
        System.out.println("Received Fluid <" + msg + ">");
        // velocityStorage.addVelocity(msg);
        JSONObject j = new JSONObject(msg);
        if(carService.existsById((long) 1)) {

        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
