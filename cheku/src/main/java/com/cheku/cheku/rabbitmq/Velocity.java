package com.cheku.cheku.rabbitmq;

import java.sql.Date;
import java.util.concurrent.CountDownLatch;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.SpeedHistory;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.TripService;
import com.cheku.cheku.service.VelocityService;

@Component
public class Velocity {

  @Autowired
  private TripService tripService;

  @Autowired
  private VelocityService velocityService;

  @Autowired
  private CarService carService;

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(byte[] message) throws ResourceNotFoundException {
    String msg = new String(message);
    //System.out.println("Received velocity <" + msg + ">");
    JSONObject j = new JSONObject(msg);
    
    //System.out.println("Car exists? " + carService.existsById((long) 1));
    if(carService.existsById((long) 1)) {
      Car car = carService.getCar((long) 1);
      Trip trip = tripService.getCarCurrentTrip(car.getId());
      SpeedHistory historyVelocity = new SpeedHistory();
      historyVelocity.setTrip(trip);
      historyVelocity.setGear(j.getInt("gear"));
      historyVelocity.setVelocity(j.getDouble("velocity"));
      historyVelocity.setRPM(j.getDouble("rpm"));
      historyVelocity.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));

      velocityService.addVelocity(historyVelocity);

      incrementDistance(trip, j.getDouble("velocity"));
    }

    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

  private void incrementDistance(Trip trip, Double speed) {
    //convert to m/s tives refresh rate (0.1s)
    Double spaceTraveled = trip.getTraveledDistance() + (speed * (5.0/18.0) * 0.1);
    //System.out.println(trip.getTraveledDistance() +  " Traveled: " + spaceTraveled);
    tripService.changeDistance(trip.getId(), spaceTraveled);
  }
}
