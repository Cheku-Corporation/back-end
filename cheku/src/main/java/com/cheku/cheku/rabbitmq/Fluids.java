package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.Fluid;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.FluidService;

@Component
public class Fluids {
    
    @Autowired
    private FluidService fluidService;

    @Autowired
    private CarService carService;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] message) throws ResourceNotFoundException {
        String msg = new String(message);
        System.out.println("Received Fluid <" + msg + ">");
        // velocityStorage.addVelocity(msg);
        JSONObject j = new JSONObject(msg);
        if(carService.existsById((long) 1)) {
            Car car = carService.getCar((long) 1);
            Fluid fluid = new Fluid();
            fluid.setCar(car);
            fluid.setFuel(j.getDouble("current_fuel"));
            fluid.setOil(j.getDouble("current_oil"));
            fluid.setWater(j.getDouble("current_water")); 
            fluid.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
      
            try {
              fluidService.addFluid(fluid);
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
