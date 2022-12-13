package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.SimpleFluid;
import com.cheku.cheku.auxiliar_classes.UserNotification;
import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Fluid;
import com.cheku.cheku.model.Notification;
import com.cheku.cheku.repository.FluidRepository;
import com.cheku.cheku.repository.NotificationRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRespository notificationRespository;

    @Autowired
    private CarService carService;

    public List<UserNotification> getAllNotifications(Long car_id) {
        return notificationRespository.getAllNotifications(car_id);
    }

    public Notification addNotification(Notification notification) throws ResourceNotFoundException {
        return notificationRespository.save(notification);
    }
}
