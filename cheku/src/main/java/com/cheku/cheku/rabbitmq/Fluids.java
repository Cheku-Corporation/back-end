package com.cheku.cheku.rabbitmq;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cheku.cheku.exception.ResourceNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.Fluid;
import com.cheku.cheku.model.Notification;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.FluidService;
import com.cheku.cheku.service.NotificationService;
import com.cheku.cheku.service.TripService;

@Component
public class Fluids {

  @Autowired
  private FluidService fluidService;

  @Autowired
  private CarService carService;

  @Autowired
  private TripService tripService;

  @Autowired
  private NotificationService notificationService;

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(byte[] message) throws ResourceNotFoundException {
    String msg = new String(message);
    // System.out.println("Received Fluid <" + msg + ">");

    JSONObject j = new JSONObject(msg);
    if (carService.existsById((long) 1)) {
      Car car = carService.getCar((long) 1);
      Trip trip = tripService.getCarCurrentTrip(car.getId());
      Fluid fluid = new Fluid();
      fluid.setTrip(trip);
      fluid.setFuel(j.getDouble("current_fuel"));
      fluid.setOil(j.getDouble("current_oil"));
      fluid.setWater(j.getDouble("current_water"));
      fluid.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
      fluidService.addFluid(fluid);

      if (fluid.getFuel() <= 0.5) {
        System.out.println("Adding not");
        Notification not = new Notification();
        not.setPriority(2);
        not.setSubject("Low Fuel");
        not.setMessage("Your car has only " + (fluid.getFuel() * 10) + "% fuel left!");
        not.setType("fuel");
        not.setCar(car);
        notificationService.addNotification(not);
      }
    }
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }
}
