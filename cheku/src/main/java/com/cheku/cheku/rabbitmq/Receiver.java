package com.cheku.cheku.rabbitmq;

import java.sql.Date;
import java.util.concurrent.CountDownLatch;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.HistoryVelocity;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.VelocityService;

@Component
public class Receiver {

  // @Autowired
  // private VelocityStorage velocityStorage;

  @Autowired
  private VelocityService velocityService;

  @Autowired
  private CarService carService;

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(byte[] message) {
    String msg = new String(message);
    //System.out.println("Received <" + msg + ">");
    // velocityStorage.addVelocity(msg);
    JSONObject j = new JSONObject(msg);
    HistoryVelocity historyVelocity = new HistoryVelocity();

    Car car = carService.getCar((long) 1);
    historyVelocity.setCar(car);

    historyVelocity.setGear(j.getInt("gear"));
    historyVelocity.setVelocity(j.getDouble("velocity"));
    historyVelocity.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
    try {
      velocityService.addVelocity(historyVelocity);
    } catch (ResourceNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }
}
