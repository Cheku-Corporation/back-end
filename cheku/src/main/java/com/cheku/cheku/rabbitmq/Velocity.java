package com.cheku.cheku.rabbitmq;

import java.sql.Date;
import java.util.concurrent.CountDownLatch;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.Notification;
import com.cheku.cheku.model.SpeedHistory;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.GroupService;
import com.cheku.cheku.service.NotificationService;
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

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private GroupService groupService;

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(byte[] message) throws ResourceNotFoundException {
    String msg = new String(message);
    //System.out.println("Received velocity <" + msg + ">");
    JSONObject j = new JSONObject(msg);
    
    if(carService.existsById(j.getLong("id"))) {
      Car car = carService.getCar(j.getLong("id"));
      Trip trip = tripService.getCarCurrentTrip(car.getId());
      SpeedHistory historyVelocity = new SpeedHistory();
      historyVelocity.setTrip(trip);
      historyVelocity.setGear(j.getInt("gear"));
      historyVelocity.setVelocity(j.getDouble("velocity"));
      historyVelocity.setRPM(j.getDouble("rpm"));
      historyVelocity.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));

      velocityService.addVelocity(historyVelocity);

      incrementDistance(trip, j.getDouble("velocity"));

      if (j.getDouble("velocity") >= 160) {
        System.out.println("Adding velocity notification");
        Notification not = new Notification();
        not.setPriority(1);
        not.setSubject("Dangerous driving");
        not.setMessage("You are driving too fast, slow down!");
        Long l = car.getGroup().getId();
        System.out.println("group id" + l + " exists? " + groupService.findGroupById(car.getGroup().getId()));
        not.setGroup(groupService.getGroupById(car.getGroup().getId()));
        notificationService.addNotification(not);
      }
    }

    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

  private void incrementDistance(Trip trip, Double speed) {
    //convert to m/s times refresh rate (0.1s)
    Double spaceTraveled = trip.getTraveledDistance() + (speed * (5.0/18.0) * 0.1) / 1000;
    tripService.changeDistance(trip.getId(), spaceTraveled);
  }
}
